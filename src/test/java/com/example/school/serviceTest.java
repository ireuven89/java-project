package com.example.school;


import com.example.school.services.GraphDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.school.services.service.findPath;

@RunWith(SpringRunner.class) 
public class serviceTest {


    private static final Map<String, GraphDto> locations = new HashMap<>();

    @Test
    public void testCase(){
        // Sample Data
        locations.put("airport-ben-gurion", new GraphDto("airport", "airport-ben-gurion",
                List.of("highway-road-1"), null));
        locations.put("tel-aviv", new GraphDto("city", "tel-aviv",
                List.of("junction-1"), List.of("only-even-numbered-can-enter")));

        // Find Path
        List<String> path = findPath("airport-ben-gurion", "tel-aviv");
        System.out.println("Path: " + path);
    }
}
