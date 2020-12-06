package ru.otus.service;

import ru.otus.domain.Quiz;
import ru.otus.domain.Student;

import java.io.IOException;
import java.util.List;

public interface CsvService {

    List<Quiz> readFile(String path) throws IOException;
    int printQuestionsAndAnswers(List<Quiz> list);
    Student askFirstAndLastNames();
    void passTestOrNot(int countCorrectPersonAnswers,int countCorrectPersonForPassTest, Student student);

}
