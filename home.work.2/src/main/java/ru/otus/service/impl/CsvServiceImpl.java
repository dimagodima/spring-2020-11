package ru.otus.service.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.otus.domain.Quiz;
import ru.otus.domain.Student;
import ru.otus.service.CsvService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;


@Service
public class CsvServiceImpl implements CsvService {

    @Override
    public List<Quiz> readFile(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        InputStream inputStream = resource.getInputStream();
        MappingIterator<Quiz> personIter = new CsvMapper().readerWithTypedSchemaFor(Quiz.class)
                .readValues(inputStream);
        return personIter.readAll();
    }

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

        Student student = new Student();
        System.out.println("What is your first Name?");
        student.setFirstName(scannerResult());
        System.out.println("What is your last Name?");
        student.setLastName(scannerResult());

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

    public String scannerResult(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
