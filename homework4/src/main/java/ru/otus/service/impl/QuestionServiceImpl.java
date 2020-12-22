package ru.otus.service.impl;

import org.springframework.stereotype.Component;
import ru.otus.config.ApplicationConfig;
import ru.otus.domain.Quiz;
import ru.otus.domain.Student;
import ru.otus.service.MessageSourceMethods;
import ru.otus.service.QuestionService;
import ru.otus.service.ScannerInput;

import java.util.List;
import java.util.Locale;

@Component
public class QuestionServiceImpl implements QuestionService {

    private final ApplicationConfig applicationConfig;
    private final ScannerInput scannerInput;
    private final MessageSourceMethods messageSourceMethods;

    public QuestionServiceImpl(ApplicationConfig applicationConfig, ScannerInput scannerInput, MessageSourceMethods messageSourceMethods) {
        this.applicationConfig = applicationConfig;
        this.scannerInput = scannerInput;
        this.messageSourceMethods = messageSourceMethods;
    }

    @Override
    public int printQuestionsAndAnswers(List<Quiz> list){
        Locale locale = applicationConfig.getLocale();

        int correctAnswerCount = 0;
        for (Quiz quiz : list) {
            String currentQuestion = messageSourceMethods.getMessage("current.question", locale);
            System.out.println(currentQuestion + quiz.getQuestion() + ".");

            if((scannerInput.scannerResult()).equalsIgnoreCase(quiz.getAnswer())) {
                String answerIsCorrect = messageSourceMethods.getMessage("answer.correct", locale);
                System.out.println(answerIsCorrect);
                correctAnswerCount++;
            }else {
                String correctAnswerIs = messageSourceMethods.getMessage("correct.answer", locale);
                System.out.println(correctAnswerIs + quiz.getAnswer() + ".");
            }
        }
        return correctAnswerCount;
    }

    @Override
    public Student askFirstAndLastNames() {
        String askFirstName = messageSourceMethods.getMessage("first.name", applicationConfig.getLocale());
        String askLastName = messageSourceMethods.getMessage("last.name",  applicationConfig.getLocale());

        System.out.println(askFirstName);
        System.out.println(askLastName);

        return new Student(scannerInput.scannerResult(), scannerInput.scannerResult());
    }
}
