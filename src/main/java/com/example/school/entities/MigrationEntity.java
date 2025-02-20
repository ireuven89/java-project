package com.example.school.entities;


import com.example.school.model.migration.MigrationStatusEnum;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "migration_Status")
public class MigrationEntity {

    @Id
    private String id;
    @Indexed
    private String migrationName;
    private String status = MigrationStatusEnum.PENDING.getValue();
    private String queueName;
    private long totalTasksCount = 0;
    private long totalTasksCompleted = 0;
    private long timeLeft;
    private long totalTasksLeft;

}
