package com.example.school.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "grades")
@Getter
@Setter
public class GradeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private int grade;
    @ManyToOne()
    @JoinColumn(name = "pupils")
    private PupilEntity pupil;
}
