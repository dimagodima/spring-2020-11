package ru.otus.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.config.ApplicationConfig;
import ru.otus.domain.Quiz;
import ru.otus.domain.Student;
import ru.otus.service.QuestionService;

import java.util.List;
import java.util.Scanner;

@Component
public class QuestionServiceImpl implements QuestionService {

    private final MessageSource messageSource;
    private final ApplicationConfig applicationConfig;

    public QuestionServiceImpl(MessageSource messageSource, ApplicationConfig applicationConfig) {
        this.messageSource = messageSource;
        this.applicationConfig = applicationConfig;
    }

    @Override
    public int printQuestionsAndAnswers(List<Quiz> list){
        int correctAnswerCount = 0;
        for (Quiz quiz : list) {
            String currentQuestion = messageSource.getMessage("current.question", new String[]{" "}, applicationConfig.getLocale());
            System.out.println(currentQuestion + quiz.getQuestion() + ".");
            if((scannerResult()).equalsIgnoreCase(quiz.getAnswer())) {
                String answerIsCorrect = messageSource.getMessage("answer.correct", new String[]{" "}, applicationConfig.getLocale());
                System.out.println(answerIsCorrect);
                correctAnswerCount++;
            }else {
                String correctAnswerIs = messageSource.getMessage("correct.answer", new String[]{" "}, applicationConfig.getLocale());
                System.out.println(correctAnswerIs + quiz.getAnswer() + ".");
            }
        }
        return correctAnswerCount;
    }

    @Override
    public Student askFirstAndLastNames() {
        String askFirstName = messageSource.getMessage("first.name", new String[]{" "}, applicationConfig.getLocale());
        String askLastName = messageSource.getMessage("last.name", new String[]{" "}, applicationConfig.getLocale());

        System.out.println(askFirstName);
        System.out.println(askLastName);

        return new Student(scannerResult(), scannerResult());
    }

    @Override
    public String scannerResult() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
