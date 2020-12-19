package ru.otus.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.config.CsvConfig;
import ru.otus.domain.Quiz;
import ru.otus.domain.Student;
import ru.otus.service.CsvService;
import ru.otus.service.PassExam;
import ru.otus.service.QuestionService;
import ru.otus.service.QuizService;

import java.io.IOException;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuestionService questionService;
    private final CsvService csvService;
    private final CsvConfig csvConfig;
    private final PassExam passExam;

    public QuizServiceImpl(QuestionService questionService, CsvService csvService, CsvConfig csvConfig, PassExam passExam){
        this.questionService = questionService;
        this.csvService = csvService;
        this.csvConfig = csvConfig;
        this.passExam = passExam;
    }

    public void startExam() {
        List<Quiz> quizList = null;
        Student student = questionService.askFirstAndLastNames();
        try {
            quizList = csvService.readFile(csvConfig.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int countCorrectAnswers = questionService.printQuestionsAndAnswers(quizList);
        passExam.passTestOrNot(countCorrectAnswers, csvConfig.getPassScore(), student);
    }

}
