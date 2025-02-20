package com.example.school.services.python;

import com.example.school.repositories.mongo.ElementsRepository;
import com.example.school.services.ElementDto;
import com.example.school.services.ElementGenerator;
import com.example.school.services.ElementsGenerator;
import com.example.school.model.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class PythonGenerator extends ElementsGenerator implements ElementGenerator {


    @Autowired
    public PythonGenerator(ElementsRepository elementsRepository) {
        super(elementsRepository);
    }

    private static final Logger logger = LoggerFactory.getLogger(PythonGenerator.class);

    @Override
    public ElementDto createElement() {
        ElementDto elementDto = new ElementDto(Language.PYTHON.getValue(), "name", "", 1, 50);

        return elementDto;
    }

    public void SaveConcurrent(List<ElementDto> elements) {
        List<CompletableFuture<Void>> futures = elements.stream()
                .map(element -> CompletableFuture.runAsync(() -> save(element)))
                .toList();

        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        } catch (Exception e) {
            logger.error("failed to execute futures");
        }
    }

    @Override
    public void generateElements() {
    }

    @Override
    public void generateElement(){

    }

}
