package ru.otus.service.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ru.otus.domain.Quiz;
import ru.otus.service.CsvService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


@Component
public class CsvServiceImpl implements CsvService {

    @Override
    public List<Quiz> readFile(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        InputStream inputStream = resource.getInputStream();
        Reader reader = new InputStreamReader(inputStream);
        List<Quiz> quizList = new ArrayList<>();
        Quiz quiz = new Quiz();
        try (
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            for(CSVRecord csvRecord : csvParser){
                quiz.setQuestion(csvRecord.get(1));
                quiz.setAnswer(csvRecord.get(0));
                quizList.add(quiz);
            }
        }
        return quizList;
    }
}
