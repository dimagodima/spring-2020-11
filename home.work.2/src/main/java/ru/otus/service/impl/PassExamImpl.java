package ru.otus.service.impl;

import org.springframework.stereotype.Component;
import ru.otus.domain.Student;
import ru.otus.service.PassExam;

@Component
public class PassExamImpl implements PassExam {
    @Override
    public void passTestOrNot(int countCorrectPersonAnswers, int countCorrectPersonForPassTest, Student student) {
        if(countCorrectPersonAnswers >= countCorrectPersonForPassTest){
            System.out.println(student.getFirstName() + " " + student.getLastName() +
                    " is passed the test. Congratulations!");
        }else {
            System.out.println(student.getFirstName() + " " + student.getLastName() +
                    " did not passed the test. Don't give up! Next time you will turn!");
        }
    }

}
