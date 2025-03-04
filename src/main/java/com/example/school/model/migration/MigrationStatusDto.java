package com.example.school.model.migration;

import lombok.Data;
@Data
public class MigrationStatusDto {

    private String migrationName;
    private String status;
    private long totalTasksCount;
    private long tasksCompleted;
    private long timeLeft;
}
