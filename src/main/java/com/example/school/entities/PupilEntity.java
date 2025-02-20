package com.example.school.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "pupils")
@NoArgsConstructor
public class PupilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "lon")
    private Double lon;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "grades")
    private List<GradeEntity> grades;
}
