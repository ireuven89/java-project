package com.example.school.repositories.mongo;


import com.example.school.entities.MigrationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MigrationRepository extends MongoRepository<MigrationEntity,  String> {

    Optional<MigrationEntity> findByMigrationName(String name);
}
