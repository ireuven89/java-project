package com.example.school.model.migration;

public enum MigrationStatusEnum {

    PENDING("PENDING"),
    STOPPED("STOPPED"),
    IN_PROGRESS("IN_PROGRESS"),
    FINISHED("FINISHED");

    private final String value;

    MigrationStatusEnum(String status){
        this.value = status;
    }

    public String getValue(){

        return this.value;
    }


}
