package ru.otus.homework1.service;

import ru.otus.homework1.domain.Quiz;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ICsvService {

    List<Quiz> readFile() throws IOException;
    void printQuestionsAndAnswers(List<Quiz> list);
}
