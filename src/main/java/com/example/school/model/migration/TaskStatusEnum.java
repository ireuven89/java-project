package com.example.school.model.migration;

public enum TaskStatusEnum {

    COMPLETED("COMPLETED"),
    IN_PROGRESS("IN_PROGRESS"),
    PENDING("PENDING");

    private final String value;

    TaskStatusEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
