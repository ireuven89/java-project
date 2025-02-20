package com.example.school.repositories.jpa;


import com.example.school.entities.GradeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends CrudRepository<GradeEntity, Long> {

    Optional<GradeEntity> findById(Long id);
/*
    @Query("SELECT id, courseName, grade FROM grades g where g.courseName  =:courseName")
    Page<GradeEntity> findByCourseName(String courseName, Pageable pageable);

     @Query("SELECT id, courseName, grade FROM grades g where g.grade  between :minGrade and :maxGrade")
    List<GradeEntity> findByGradeBetween(@Param("min") int gradeMin, @Param("maxGrade") int gradeMax);*/

}