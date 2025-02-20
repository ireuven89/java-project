package com.example.school.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Location {
    String type;
    String id;
    Map<String, String> attrs;
    List<String> exits;
    List<String> rules;

    public Location(String type, String id, List<String> exits, List<String> rules) {
        this.type = type;
        this.id = id;
        this.attrs = new HashMap<>();
        this.exits = exits;
        this.rules = rules != null ? rules : new ArrayList<>();
    }
}
