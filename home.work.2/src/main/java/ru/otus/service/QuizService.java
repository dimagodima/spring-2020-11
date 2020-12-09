package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.config.CsvConfig;
import ru.otus.domain.Quiz;
import ru.otus.domain.Student;

import java.io.IOException;
import java.util.List;

@Service
public class QuizService {

    private final CsvService csvService;
    private final CsvConfig csvConfig;

    @Autowired
    public QuizService(CsvService csvService, CsvConfig csvConfig){
        this.csvService = csvService;
        this.csvConfig = csvConfig;
    }

    public void startExam() {
        List<Quiz> quizList = null;
        Student student = csvService.askFirstAndLastNames();
        try {
            quizList = csvService.readFile(csvConfig.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int countCorrectAnswers = csvService.printQuestionsAndAnswers(quizList);
        csvService.passTestOrNot(countCorrectAnswers, csvConfig.getPassScore(), student);
    }

}
