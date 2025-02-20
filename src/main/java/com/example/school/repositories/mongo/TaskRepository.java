package com.example.school.repositories.mongo;

import com.example.school.entities.TaskEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<TaskEntity, Long> {


    List<TaskEntity> findByStatus(String status, Pageable page);
    long countByStatus(String pending);

}
