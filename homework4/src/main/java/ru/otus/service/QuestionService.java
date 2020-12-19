package ru.otus.service;

import ru.otus.domain.Quiz;
import ru.otus.domain.Student;

import java.util.List;

public interface QuestionService {
    int printQuestionsAndAnswers(List<Quiz> list);
    Student askFirstAndLastNames();
    String scannerResult();
}
