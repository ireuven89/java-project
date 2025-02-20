package com.example.school.services;


import com.example.school.entities.ElementEntity;
import com.example.school.model.Language;
import com.example.school.repositories.mongo.ElementsRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public abstract class ElementsGenerator {

    protected final ElementsRepository elementsRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ElementsGenerator(ElementsRepository elementsRepository) {
        this.elementsRepository = elementsRepository;
    }

    @PostConstruct
    private void init() {
        modelMapper.addMappings(new PropertyMap<ElementDto, ElementEntity>() {
            @Override
            protected void configure() {
                map().setName(source.getName());
                map().setLanguage(source.getLanguage());
                map().setVersion(source.getVersion());
                map().setStartLine(source.getStartLine());
                map().setEndLine(source.getEndLine());
            }
        });
    }

    public abstract ElementDto createElement();

    public List<ElementDto> getElements(Language language, PageRequest request) {
        List<ElementEntity> elementEntities = elementsRepository.findAllByLanguage(language.getValue(), request);

        List<ElementDto> result = elementEntities.stream().
                map(x -> new ElementDto(x.getLanguage(), x.getName(), x.getVersion(), x.getStartLine(), x.getEndLine())).
                collect(Collectors.toList());

        return result;
    }

    public void saveAll(List<ElementDto> elements) {
        List<ElementEntity> entities = toEntities(elements);
        try {
            elementsRepository.saveAll(entities);
        } catch (Exception e) {
            throw new RuntimeException("failed to save");
        }
    }

    public void save(ElementDto elementDto) {
        try {
            ElementEntity entity = new ElementEntity();
            modelMapper.map(elementDto, entity);
            elementsRepository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("failed to save: " + e.getMessage());
        }
    }

    protected List<ElementEntity> toEntities(List<ElementDto> elements) {

        List<ElementEntity> entities = elements.stream().
                map(x -> {
                    try {
                        ElementEntity entity = new ElementEntity();
                        modelMapper.map(x, entity);
                        return entity;
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to parse entities");
                    }
                }).
                collect(Collectors.toList());

        return entities;
    }

}
