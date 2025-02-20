package com.example.school.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "school")
public class SchoolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "Integer default 1")
    private Integer minimumGpa;
    private Integer maxNumberOfPupils;
}
