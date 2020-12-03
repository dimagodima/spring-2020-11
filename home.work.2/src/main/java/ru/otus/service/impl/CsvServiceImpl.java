package ru.otus.service.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.otus.domain.Student;
import ru.otus.domain.Quiz;
import ru.otus.service.ICsvService;

import java.io.*;
import java.util.List;
import java.util.Scanner;


@Service
public class CsvServiceImpl implements ICsvService {

    @Override
    public List<Quiz> readFile(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        InputStream inputStream = resource.getInputStream();
        MappingIterator<Quiz> personIter = new CsvMapper().readerWithTypedSchemaFor(Quiz.class)
                .readValues(inputStream);
        return personIter.readAll();
    }

    @Override
    public int printQuestionsAndAnswers(List<Quiz> list, Student student){

        int correctAnswerCount = 0;

        for (Quiz quiz : list) {
            System.out.println("Current question is: " + quiz.getQuestion() + ".");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if((answer.toLowerCase()).equals(quiz.getAnswer().toLowerCase())) {
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

        Student student = new Student();
        System.out.println("What is your first Name?");
        Scanner scanner = new Scanner(System.in);
        student.setFirstName(scanner.nextLine());
        System.out.println("What is your last Name?");
        student.setLastName(scanner.nextLine());

        return student;
    }

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