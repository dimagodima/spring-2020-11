package ru.otus.service;

import ru.otus.domain.Quiz;
import ru.otus.domain.Student;

import java.io.IOException;
import java.util.List;

public interface CsvService {

    List<Quiz> readFile(String path) throws IOException;
}
