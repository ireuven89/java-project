package com.example.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TaskService {

    @Autowired
    private AsyncService asyncService;

    private final Logger logger = LoggerFactory.getLogger(TaskService.class);

    public void taskExecutor(){
        List<CompletableFuture<String>> futures = new ArrayList<>();

        for(int i =0; i < 100; i++){
            futures.add(asyncService.processTask("task number" + i ));
        }

        futures.forEach(x -> {
            try {
                x.get();
            }catch (Exception e){
                logger.error("failed executing task {}", e.getMessage());
            }
        });

    }
}
