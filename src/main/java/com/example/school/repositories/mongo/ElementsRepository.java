package com.example.school.repositories.mongo;


import com.example.school.entities.ElementEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ElementsRepository extends MongoRepository<ElementEntity, String> {


    Optional<ElementEntity> findById(String id);

    List<ElementEntity> findAllByName(String name);

    List<ElementEntity> findAllByVersion(String version);

    List<ElementEntity> findAllByLanguage(String language, Pageable pageable);
}
