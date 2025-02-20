package com.example.school.services;

import java.util.*;

public class GraphDto {
    String type;
    String id;
    Map<String, String> attrs;
    List<String> exits;
    List<String> rules;

    public GraphDto(String type, String id, List<String> exits, List<String> rules) {
        this.type = type;
        this.id = id;
        this.attrs = new HashMap<>();
        this.exits = exits;
        this.rules = rules != null ? rules : new ArrayList<>();
    }



}



