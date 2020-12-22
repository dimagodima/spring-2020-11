package ru.otus.service.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import ru.otus.domain.Quiz;
import ru.otus.service.CsvService;
import ru.otus.service.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvServiceImpl implements CsvService {

    @Override
    public List<Quiz> readFile(String path) throws IOException {
        List<Quiz> quizList = new ArrayList<>();
        try (CSVParser csvParser = new CSVParser(Utils.readFileToReaderFromResource(path), CSVFormat.DEFAULT)) {
            for (CSVRecord csvRecord : csvParser) {
                Quiz quiz = new Quiz();
                quiz.setQuestion(csvRecord.get(1));
                quiz.setAnswer(csvRecord.get(0));
                quizList.add(quiz);
            }
        }
        return quizList;
    }
}
