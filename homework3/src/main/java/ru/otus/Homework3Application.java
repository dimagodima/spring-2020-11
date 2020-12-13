package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.service.QuizService;

@SpringBootApplication
public class Homework3Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Homework3Application.class, args);
        QuizService quizService = run.getBean(QuizService.class);
        quizService.startExam();
    }
}
