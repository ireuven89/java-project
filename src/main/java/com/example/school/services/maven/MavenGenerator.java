package com.example.school.services.maven;


import com.example.school.repositories.mongo.ElementsRepository;
import com.example.school.services.ElementDto;
import com.example.school.services.ElementGenerator;
import com.example.school.services.ElementsGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MavenGenerator extends ElementsGenerator implements ElementGenerator {


    @Autowired
    public MavenGenerator(ElementsRepository elementsRepository) {
        super(elementsRepository);
    }

    public void generateElements() {

    }

    @Override
    public void generateElement(){

    }

    @Override
    public ElementDto createElement() {


        return null;
    }
}
