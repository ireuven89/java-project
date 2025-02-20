package com.example.school.controller;

import com.example.school.handler.ResourceNotFoundException;
import com.example.school.resources.PupilResource;
import com.example.school.services.PupilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@Controller
@RequestMapping("/api/v1/pupil")
public class PupilController {

    @Autowired
    private PupilService pupilService;

    @PostMapping("/pupil")
    public ResponseEntity<Long> createPupil(@RequestBody PupilResource pupil) {

        try {
            Long id = pupilService.createPupil(pupil);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pupil/{id}")
    public ResponseEntity<PupilResource> getPupil(@PathVariable Long id) {

        try {
            PupilResource resource = pupilService.getPupil(id);
            return new ResponseEntity<>(resource, HttpStatus.OK);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pupil/{id}")
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> updatePupil(@PathVariable Long id,
                                              @RequestBody PupilResource pupil) {
        try {
            pupilService.updatePupil(id, pupil);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/pupil/{id}")
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> deletePupil(@PathVariable Long id) {
        try {
            pupilService.deletePupil(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
