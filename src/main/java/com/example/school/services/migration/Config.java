package com.example.school.services.migration;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {


 /*   @Value("spring.data.mongodb.host")
    String mongoHost;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(String.format("mongodb://%s:27017", mongoHost)); // Change to your MongoDB URL
    }

    @Bean
    public MongoTemplate queueMongoTemplate() {
        return new MongoTemplate(mongoClient(), "migration-queues"); // Replace with your DB name
    }*/
}
