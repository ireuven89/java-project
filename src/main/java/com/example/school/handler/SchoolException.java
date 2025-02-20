package com.example.school.handler;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SchoolException extends Exception{

    List<Object> args = new ArrayList<>();
    public SchoolException(String message, Object... args){
       super(message);

       this.args.addAll(Arrays.asList(args));
    }
}
