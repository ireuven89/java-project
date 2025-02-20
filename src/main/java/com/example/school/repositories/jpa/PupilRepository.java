package com.example.school.repositories.jpa;

import com.example.school.entities.PupilEntity;
import jakarta.persistence.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Cacheable
public interface PupilRepository extends CrudRepository<PupilEntity, Long> {

    Optional<PupilEntity> findById(Long id);

/*   @Query("SELECT id, courseName, grade FROM pupils p " +
            "JOIN grades g ON p.grade_id = g.id " +
            "where g.grade  > :minGrade and g.grade < :maxGrade")*/
  //  List<PupilEntity> findAllByGradesBetween(@Param("minGrade") Integer minGrade,@Param("maxGrade") Integer maxGrade);

    @Override
    <S extends PupilEntity> S save(S entity);
}
