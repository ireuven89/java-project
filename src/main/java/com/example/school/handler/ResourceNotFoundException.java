package com.example.school.handler;

public class ResourceNotFoundException extends SchoolException {

    public ResourceNotFoundException(String message, Object... args){
        super(message, args);
    }
}
