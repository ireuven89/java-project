package com.example.school.resources;

import com.example.school.services.PupilService;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
public class PupilResource {
    private Double lat;
    private Double lon;
    private List<GradeResource> grades = new ArrayList<>();
    private List<String> friends;

    public PupilResource(Double lat, Double lon, List<GradeResource> grades, List<String> friends){
        this.lat = lat;
        this.lon = lon;
        this.grades = grades;
        this.friends = friends;
    }

}




