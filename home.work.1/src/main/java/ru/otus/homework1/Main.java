package ru.otus.homework1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import ru.otus.homework1.domain.Quiz;
import ru.otus.homework1.service.CsvServiceImpl;

import java.io.InputStream;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        CsvServiceImpl csvService = context.getBean(CsvServiceImpl.class);
        List<Quiz> quizList = csvService.readFile();
        csvService.printQuestionsAndAnswers(quizList);
        context.close();
    }

}
