package ru.otus.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.config.ApplicationConfig;
import ru.otus.domain.Student;
import ru.otus.service.PassExam;

@Component
public class PassExamImpl implements PassExam {

    private final MessageSource messageSource;
    private final ApplicationConfig applicationConfig;

    public PassExamImpl(MessageSource messageSource, ApplicationConfig applicationConfig) {
        this.messageSource = messageSource;
        this.applicationConfig = applicationConfig;
    }

    @Override
    public void passTestOrNot(int countCorrectPersonAnswers, int countCorrectPersonForPassTest, Student student) {
        if (countCorrectPersonAnswers >= countCorrectPersonForPassTest) {
            String testIsPassed = messageSource.getMessage("test.passed", new String[]{" "}, applicationConfig.getLocale());
            System.out.println(student.getFirstName() + " " + student.getLastName() + testIsPassed);
        } else {
            String testDontPassed = messageSource.getMessage("not.pass", new String[]{" "}, applicationConfig.getLocale());
            System.out.println(student.getFirstName() + " " + student.getLastName() + testDontPassed);
        }
    }

}
