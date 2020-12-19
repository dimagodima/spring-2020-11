package ru.otus.service.impl;

import org.springframework.stereotype.Component;
import ru.otus.domain.Quiz;
import ru.otus.domain.Student;
import ru.otus.service.QuestionService;

import java.util.List;
import java.util.Scanner;

@Component
public class QuestionServiceImpl implements QuestionService {

    @Override
    public int printQuestionsAndAnswers(List<Quiz> list){
        int correctAnswerCount = 0;
        for (Quiz quiz : list) {
            System.out.println("Current question is: " + quiz.getQuestion() + ".");
            if((scannerResult().toLowerCase()).equals(quiz.getAnswer().toLowerCase())) {
                System.out.println("Answer is correct");
                correctAnswerCount++;
            }else {
                System.out.println("Correct answer is: " + quiz.getAnswer() + ".");
            }
        }

        return correctAnswerCount;
    }

    @Override
    public Student askFirstAndLastNames() {
        System.out.println("What is your first Name?");
        System.out.println("What is your last Name?");
        Student student = new Student(scannerResult(),scannerResult());

        return student;
    }

    @Override
    public String scannerResult(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
