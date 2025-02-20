package com.example.school.entities;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "migration")
public class TaskEntity {

    @Id
    private String id;

    private String name;

    private String status;

}
