package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.config.CsvConfig;
import ru.otus.domain.Student;
import ru.otus.domain.Quiz;
import ru.otus.service.ICsvService;

import java.io.IOException;
import java.util.List;

@ComponentScan
@Configuration
public class Application {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class);
        List<Quiz> quizList = null;
        ICsvService csvService = context.getBean(ICsvService.class);
        CsvConfig csvConfig = context.getBean(CsvConfig.class);

        Student student = csvService.askFirstAndLastNames();
        try {
            quizList = csvService.readFile(csvConfig.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        int countCorrectAnswers = csvService.printQuestionsAndAnswers(quizList, student);

        csvService.passTestOrNot(countCorrectAnswers, csvConfig.getPassScore(), student);


    }






}