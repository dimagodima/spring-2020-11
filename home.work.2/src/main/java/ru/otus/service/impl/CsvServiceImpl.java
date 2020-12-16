package ru.otus.service.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.otus.domain.Quiz;
import ru.otus.domain.Student;
import ru.otus.service.CsvService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;


@Component
public class CsvServiceImpl implements CsvService {

    @Override
    public List<Quiz> readFile(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        InputStream inputStream = resource.getInputStream();
        MappingIterator<Quiz> personIter = new CsvMapper().readerWithTypedSchemaFor(Quiz.class)
                .readValues(inputStream);
        return personIter.readAll();
    }
}
