package com.example.school.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncService.class);

    @Async  // Runs in a separate thread
    public CompletableFuture<String> processTask(String taskName) {
        logger.info("Processing task: {} in thread: {}", taskName, Thread.currentThread().getName());

        try {
            Thread.sleep(3000);  // Simulating a long-running task
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return CompletableFuture.completedFuture("Completed: " + taskName);
    }
}
