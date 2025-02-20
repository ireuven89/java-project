package com.example.school.services;

import java.util.HashMap;
import java.util.Map;

public class InMemCache {

    private static Integer capacity = 0;
    private final Map<Object, Object> inMemCache = new HashMap<>();

    public InMemCache(Integer capacity) {
        InMemCache.capacity = capacity;
    }

    public void Set(Object key, Object value) throws Exception {

        if (key == null) {
            throw new Exception("invalid key");
        }
        if (inMemCache.entrySet().size() == capacity) {
            throw new Exception("exceed capacity limit");
        }

        inMemCache.put(key, value);
    }

    public Object Get(Object key) throws Exception {

        if (!inMemCache.containsKey(key)) {
            throw new RuntimeException("key does not exists in  in mem cache");
        }

        return inMemCache.get(key);
    }
}
