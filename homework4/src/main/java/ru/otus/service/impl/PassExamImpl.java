package ru.otus.service.impl;

import org.springframework.stereotype.Component;
import ru.otus.config.ApplicationConfig;
import ru.otus.domain.Student;
import ru.otus.service.MessageSourceMethods;
import ru.otus.service.PassExam;

@Component
public class PassExamImpl implements PassExam {

    private final ApplicationConfig applicationConfig;
    private final MessageSourceMethods messageSourceMethods;


    public PassExamImpl(ApplicationConfig applicationConfig, MessageSourceMethods messageSourceMethods) {
        this.applicationConfig = applicationConfig;
        this.messageSourceMethods = messageSourceMethods;
    }

    @Override
    public void passTestOrNot(int countCorrectPersonAnswers, int countCorrectPersonForPassTest, Student student) {
        if (countCorrectPersonAnswers >= countCorrectPersonForPassTest) {
            String testIsPassed = messageSourceMethods.getMessage("test.passed", applicationConfig.getLocale());
            System.out.println(student.getFirstName() + " " + student.getLastName() + testIsPassed);
        } else {
            String testDontPassed = messageSourceMethods.getMessage("not.pass", applicationConfig.getLocale());
            System.out.println(student.getFirstName() + " " + student.getLastName() + testDontPassed);
        }
    }

}
