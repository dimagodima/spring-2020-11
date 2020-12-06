package ru.otus.homework1.service;


import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import ru.otus.homework1.domain.Quiz;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CsvServiceImpl implements ICsvService{

    private InputStream path;

    public void setPath(InputStream path) {
        this.path = path;
    }

    @Override
    public List<Quiz> readFile() throws IOException {
        MappingIterator<Quiz> personIter = new CsvMapper().readerWithTypedSchemaFor(Quiz.class).readValues(path);
        return personIter.readAll();
    }

    @Override
    public void printQuestionsAndAnswers(List<Quiz> list){

            for (Quiz quiz : list) {
        System.out.println("Current question is: " + quiz.getQuestion() + ".");
        System.out.println("Correct answer is: " + quiz.getAnswer() + ".");

        }
    }

}
