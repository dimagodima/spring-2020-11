package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.config.ApplicationConfig;
import ru.otus.config.CsvConfig;
import ru.otus.domain.Quiz;
import ru.otus.domain.Student;

import java.io.IOException;
import java.util.List;

@Service
public class QuizService {

    private final CsvService csvService;
    private final CsvConfig csvConfig;
    private final ApplicationConfig applicationConfig;

    @Autowired
    public QuizService(CsvService csvService, CsvConfig csvConfig, ApplicationConfig applicationConfig){
        this.csvService = csvService;
        this.csvConfig = csvConfig;
        this.applicationConfig = applicationConfig;
    }

    public void startExam() {
        List<Quiz> quizList = null;
        Student student = csvService.askFirstAndLastNames();
        try {
            if("ru_RU".equals(applicationConfig.getLocale().toString())) {
                quizList = csvService.readFile(csvConfig.getRupath());
            }
            else {
                quizList = csvService.readFile(csvConfig.getEnpath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int countCorrectAnswers = csvService.printQuestionsAndAnswers(quizList);
        csvService.passTestOrNot(countCorrectAnswers, csvConfig.getScore(), student);
    }
}
