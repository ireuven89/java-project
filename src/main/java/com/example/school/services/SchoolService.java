package com.example.school.services;


import com.example.school.entities.SchoolEntity;
import com.example.school.handler.ResourceNotFoundException;
import com.example.school.handler.SchoolException;
import com.example.school.repositories.jpa.SchoolRepository;
import com.example.school.resources.SchoolResource;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    private final ModelMapper modelMapper = new ModelMapper();
    private final Logger logger = LoggerFactory.getLogger(SchoolService.class);


    /**
     * @param id
     * @return SchoolResource if exists
     * @throws SchoolException
     */
    public SchoolResource getSchool(Long id) throws SchoolException {

        try {
            Optional<SchoolEntity> optional = schoolRepository.findById(id);
            if (optional.isEmpty()) {

                SchoolResource resource = toResource(optional.get());

                return resource;
            }

        } catch (Exception e) {
            logger.error("failed to retrieve school");
            throw new SchoolException("failed to create school", e);
        }

        return null;
    }

    /**
     * @param schoolResource
     * @return
     * @throws SchoolException
     */
    public Long createSchool(SchoolResource schoolResource) throws SchoolException {
        Long id = 0L;

        try {
            SchoolEntity entity = toEntity(schoolResource);
            schoolRepository.save(entity);
        } catch (Exception e) {
            logger.error("failed to retrieve school");
            throw new SchoolException("failed to create school", e);
        }

        return id;
    }


    /**
     * @param schoolResource
     * @throws SchoolException
     */
    public void updateSchool(Long id, SchoolResource schoolResource) throws SchoolException {
        Optional<SchoolEntity> optional = schoolRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("school not found");
        }

        try {
            SchoolEntity entity = optional.get();
            modelMapper.map(schoolResource, entity);
            schoolRepository.save(entity);
        } catch (Exception e) {
            logger.error("failed to retrieve school");
            throw new SchoolException("failed to create school", e);
        }

    }

    /**
     *
     * @param id - param
     */
    public void deleteSchool(Long id) {

        Optional<SchoolEntity> optional = schoolRepository.findById(id);

        if (optional.isEmpty()) {
            return;
        }

        schoolRepository.delete(optional.get());
    }

    private SchoolEntity toEntity(SchoolResource resource) {
        SchoolEntity entity = new SchoolEntity();
        modelMapper.map(resource, entity);


        return entity;
    }


    private SchoolResource toResource(SchoolEntity entity) {
        SchoolResource resource = new SchoolResource();
        modelMapper.map(entity, resource);


        return resource;
    }


    /**
     *
     * @param elementDtos - elements
     */
    private void generateElements(List<ElementDto> elementDtos) {
        List<Future<Void>> futures = new ArrayList<>();

        futures.forEach(x -> {
            try {
                x.get();
            } catch (Exception e) {
                logger.error("failed to get task {}", x);
            }
        });
    }



}
