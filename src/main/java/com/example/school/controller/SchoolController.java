package com.example.school.controller;


import com.example.school.resources.SchoolResource;
import com.example.school.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;


    @GetMapping("/school/{id}")
    public ResponseEntity<SchoolResource> getSchool(@PathVariable Long id) {

        try {
            SchoolResource resource = schoolService.getSchool(id);

            return new ResponseEntity<>(resource, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/school")
    public ResponseEntity<Long> createSchool(@RequestBody SchoolResource schoolResource) {

        try {
            Long id = schoolService.createSchool(schoolResource);

            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/school/{id}")
    public ResponseEntity<String> updateSchool(
            @PathVariable Long id,
            @RequestBody SchoolResource schoolResource) {

        try {
            schoolService.updateSchool(id, schoolResource);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/school/{id}")
    public ResponseEntity<String> deleteSchool(@PathVariable Long id) {

        try {
            schoolService.deleteSchool(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
