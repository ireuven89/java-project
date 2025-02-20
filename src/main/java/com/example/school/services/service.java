package com.example.school.services;

import java.util.*;

public class service {
    private static final Map<String, Location> locations = new HashMap<>();

    public static List<String> findPath(String startId, String endId) {
        if (!locations.containsKey(startId) || !locations.containsKey(endId)) {
            return List.of("Invalid locations");
        }

        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(List.of(startId));

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String current = path.get(path.size() - 1);

            if (current.equals(endId)) {
                return path;
            }

            visited.add(current);

            for (String exit : locations.get(current).exits) {
                if (!visited.contains(exit)) {
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(exit);
                    queue.add(newPath);
                }
            }
        }

        return List.of("No path found");
    }
}
