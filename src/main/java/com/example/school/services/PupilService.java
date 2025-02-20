package com.example.school.services;

import com.example.school.entities.GradeEntity;
import com.example.school.entities.PupilEntity;
import com.example.school.handler.ResourceNotFoundException;
import com.example.school.repositories.jpa.PupilRepository;
import com.example.school.resources.GradeResource;
import com.example.school.resources.PupilResource;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PupilService {

    @Autowired
    private PupilRepository pupilRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final Logger logger = LoggerFactory.getLogger(PupilService.class);

    public Long createPupil(PupilResource pupil) {
        try {
            PupilEntity entity = toEntity(pupil);
            entity = pupilRepository.save(entity);


            return entity.getId();
        } catch (Exception e) {
            logger.error("failed to create pupil resource: {}", e.getMessage());
            throw new RuntimeException();
        }
    }


    @Cacheable(value = {"pupils"}, key = "#id")
    public PupilResource getPupil(Long id) throws Exception {
        Optional<PupilEntity> entity = pupilRepository.findById(id);

        if (entity.isEmpty()) {
            logger.warn("resource not found on get pupil");
            throw new ResourceNotFoundException("resource with Id not found", id);
        }

        return toResource(entity.get());
    }

 /*   @Cacheable(value = {"pupils"}, key = "#id")
    public List<PupilResource> getPupils(Integer min, Integer max) throws Exception {
        List<PupilEntity> entities = pupilRepository.findAllByGradesBetween(min, max);

        if (entities.isEmpty()) {
            logger.warn("resources not found on get pupil");
            throw new ResourceNotFoundException("resource with Id not found", entities);
        }

        return entities.stream().
                map(this::toResource).
                collect(Collectors.toList());
    }
*/
    public void updatePupil(Long id, PupilResource resource) throws Exception {
        Optional<PupilEntity> optional = pupilRepository.findById(id);

        if (optional.isEmpty()) {
            throw new Exception("pupil not found");
        }

        PupilEntity entity = optional.get();
        modelMapper.map(resource, entity);


        pupilRepository.save(entity);
    }

    public void deletePupil(Long id) throws Exception {
        Optional<PupilEntity> optional = pupilRepository.findById(id);

        if (optional.isEmpty()) {
            throw new Exception("pupil not found");
        }

        PupilEntity entity = optional.get();
        pupilRepository.delete(entity);
    }

    public PupilEntity toEntity(PupilResource resource) {
        PupilEntity entity = new PupilEntity();
        entity.setLon(resource.getLon());
        entity.setLat(resource.getLat());
        List<GradeEntity> gradeEntities = toGradesEntity(resource);
        entity.setGrades(gradeEntities);

        return entity;
    }

    private PupilResource toResource(PupilEntity entity) {
        PupilResource resource = new PupilResource();

        resource.setLon(entity.getLon());
        resource.setLat(entity.getLat());
        entity.getGrades().forEach(x -> {
            GradeResource grade = new GradeResource();
            grade.setGrade(x.getGrade());
            grade.setCourseName(x.getCourseName());
            resource.getGrades().add(grade);
        });

        return resource;
    }

    private List<GradeEntity> toGradesEntity(PupilResource resource) {
        List<GradeEntity> entities = new ArrayList<>();

        for (int i = 0; i < resource.getGrades().size(); i++) {
            GradeEntity entity = new GradeEntity();
            entity.setGrade(resource.getGrades().get(0).getGrade());
            entity.setCourseName(resource.getGrades().get(0).getCourseName());
            entities.add(entity);
        }

        return entities;
    }
}
