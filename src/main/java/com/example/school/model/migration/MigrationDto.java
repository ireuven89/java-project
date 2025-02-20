package com.example.school.model.migration;

import lombok.Data;

@Data
public class MigrationDto {

    private String migrationName;
    private String queueName;
    private String migrationStatus;
}
