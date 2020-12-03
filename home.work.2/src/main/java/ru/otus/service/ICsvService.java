package ru.otus.service;

import ru.otus.domain.Student;
import ru.otus.domain.Quiz;

import java.io.IOException;
import java.util.List;

public interface ICsvService {

    List<Quiz> readFile(String path) throws IOException;
    int printQuestionsAndAnswers(List<Quiz> list, Student student);
    Student askFirstAndLastNames();
    void passTestOrNot(int countCorrectPersonAnswers,int countCorrectPersonForPassTest, Student student);

}
