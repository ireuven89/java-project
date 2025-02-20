package com.example.school.services.npm;

import com.example.school.repositories.mongo.ElementsRepository;
import com.example.school.services.ElementDto;
import com.example.school.services.ElementGenerator;
import com.example.school.services.ElementsGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NpmGenerator extends ElementsGenerator implements ElementGenerator {


    @Autowired
    public NpmGenerator(ElementsRepository elementsRepository) {
        super(elementsRepository);
    }

    @Override
    public void generateElements() {

    }

    @Override
    public ElementDto createElement() {


        return null;
    }

    @Override
    public void generateElement(){

    }


}
