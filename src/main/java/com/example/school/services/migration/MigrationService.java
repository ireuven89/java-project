package com.example.school.services.migration;

import com.example.school.entities.MigrationEntity;
import com.example.school.entities.TaskEntity;
import com.example.school.model.migration.MigrationStatusEnum;
import com.example.school.model.migration.TaskStatusEnum;
import com.example.school.repositories.mongo.MigrationRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MigrationService {


    @Autowired
    private MigrationRepository migrationRepository;

    private final MongoClient mongoClient = MongoClients.create();
    private final MongoTemplate queuesMongoTemplate = new MongoTemplate(mongoClient, "migration-queues");
    protected final MongoTemplate migrationsMongoTemplate = new MongoTemplate(mongoClient, "example");
    protected final String migrationCollection = "migration_Status";
    private final String migrationNameField = "migrationName";
    private static final Logger logger = LoggerFactory.getLogger(MigrationService.class);
    private final ExecutorService executorService = Executors.newFixedThreadPool(50); // 50 threads for processing tasks
    private static final int BATCH_SIZE = 1000; // Process in batches of 1000 tasks
    private static final int MAX_RETRIES = 3;
    private boolean stopMigration = false;

    @Transactional
    public void processTasks(String migration, String queueName) {
        int processedCount = 0;
        int pageNumber = 0;
        Date startTime = new Date();
        Pageable page = PageRequest.of(pageNumber, BATCH_SIZE);
        logger.info("started migration {}", migration);
        while (!stopMigration) {
            List<TaskEntity> tasks = getTasks(queueName, MigrationStatusEnum.PENDING.getValue(), page);

            if (tasks.isEmpty()) {
                logger.info("No more tasks to process");
                updateMigration(migration, MigrationStatusEnum.FINISHED);
                logger.info("finished migration total count is {}", getTotalTasksCount(queueName));
                break;
            }

            tasks.forEach(task -> {
                executorService.submit(() -> {
                    boolean success = false;
                    int retries = 0;
                    while (!success && retries < MAX_RETRIES) {
                        try {
                            task.setStatus("IN_PROGRESS");
                            queuesMongoTemplate.save(task, queueName);
                            // Simulate task execution
                            Thread.sleep(1);  // Simulate work

                            task.setStatus("COMPLETED");
                            queuesMongoTemplate.save(task, queueName);
                            logger.debug("Task " + task.getName() + " completed.");
                            success = true;  // If processing was successful, exit retry loop

                        } catch (InterruptedException e) {
                            System.err.println("Error processing task: " + task.getName());
                        } catch (Exception e) {
                            retries++;
                            System.err.println("Error on task " + task.getName() + ", retrying... (" + retries + "/" + MAX_RETRIES + ")");
                            if (retries == MAX_RETRIES) {
                                System.err.println("Failed to process task " + task.getName() + " after " + MAX_RETRIES + " retries.");
                            }
                        }
                    }
                });
            });

            processedCount += tasks.size();
            pageNumber++;
            logger.info("Processed " + processedCount + " tasks...");
            if (getMigrationStatus(migration).contains(MigrationStatusEnum.STOPPED.getValue())) {
                stopMigration = true;
                logger.info("stopping migration processed {}", processedCount);
            }
        }

        Date endTime = new Date();
        logger.info("Task processing complete took {}", endTime.getTime() - startTime.getTime());
        System.out.println();
    }

    public void shutDown() {
        executorService.shutdown();
    }

    public boolean isShutdown() {
        return executorService.isShutdown();
    }

    public List<TaskEntity> getTasks(String queue, String status, Pageable pageable) {
        // Define the query criteria for finding tasks by status
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(status));  // Replace "status" with the field you are querying on
        query.with(pageable);// Set the number of documents to retrieve

        // Find and return the documents
        List<TaskEntity> entities = queuesMongoTemplate.find(query, TaskEntity.class, queue);
        return entities;
    }

    public long getTotalTasksCount(String queue) {
        Query query = new Query();

        return queuesMongoTemplate.count(query, queue);
    }

    public long getTasksCompletedCount(String queue) {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(TaskStatusEnum.COMPLETED.getValue()));

        return queuesMongoTemplate.count(query, queue);
    }

    public void updateMigration(String name, MigrationStatusEnum status){
        Query query = new Query();
        query.addCriteria(Criteria.where(migrationNameField).is(name));
        Update update = new Update().set("status", status.getValue());
        long count = migrationsMongoTemplate.updateFirst(query, update, migrationCollection).getModifiedCount();
        logger.info("updated status {}", count);
    }

    public MigrationEntity getMigrationEntity(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where(migrationNameField).is(name));
        MigrationEntity entity = migrationsMongoTemplate.findOne(query, MigrationEntity.class, migrationCollection);

        return entity;
    }

    public String getMigrationStatus(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where(migrationNameField).is(name)).fields().include("status");
        String status = migrationsMongoTemplate.findOne(query, String.class, migrationCollection);

        return status;
    }

}
