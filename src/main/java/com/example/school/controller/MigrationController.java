package com.example.school.controller;


import com.example.school.handler.ResourceNotFoundException;
import com.example.school.model.migration.MigrationDto;
import com.example.school.model.migration.MigrationStatusDto;
import com.example.school.services.migration.MigrationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/migration")
public class MigrationController {

    @Autowired
    private MigrationManager migrationManager;

    @PostMapping("/start/{migrationName}")
    public ResponseEntity<String> startMigration(@PathVariable String migrationName) {

        try {
            migrationManager.startMigration(migrationName);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>("Started Migration", HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> createMigration(@RequestBody MigrationDto migrationDto) {

        try {
            migrationManager.createMigration(migrationDto);
            return new ResponseEntity<>("Created Migration", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/stop/{migration}")
    public ResponseEntity<String> stopMigration(@PathVariable("migration") String migration) {

        try {
            migrationManager.stopMigration(migration);
            return new ResponseEntity<>(String.format("Stopped migration %s", migration), HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException rce) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status/{name}")
    public ResponseEntity<MigrationStatusDto> migrationStatus(@PathVariable("name") String migrationName) {
        try {
            MigrationStatusDto statusDto = migrationManager.getMigrationStatus(migrationName);
            return new ResponseEntity<>(statusDto, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
