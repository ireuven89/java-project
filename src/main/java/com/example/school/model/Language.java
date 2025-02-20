package com.example.school.model;

public enum Language {


    NPM("NPM"),
    PYTHON("PYTHON"),
    JAVA("JAVA");

    Language(String language){

    }

    public String getValue(){

        return this.name();
    }
}
