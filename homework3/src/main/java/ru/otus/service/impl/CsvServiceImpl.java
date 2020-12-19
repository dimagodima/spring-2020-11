package ru.otus.service.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.otus.config.ApplicationConfig;
import ru.otus.domain.Quiz;
import ru.otus.domain.Student;
import ru.otus.service.CsvService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class CsvServiceImpl implements CsvService {

    private final MessageSource messageSource;
    private final ApplicationConfig applicationConfig;

    @Autowired
    public CsvServiceImpl(MessageSource messageSource, ApplicationConfig applicationConfig) {
        this.messageSource = messageSource;
        this.applicationConfig = applicationConfig;
    }

    @Override
    public List<Quiz> readFile(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        InputStream inputStream = resource.getInputStream();
        Reader reader = new InputStreamReader(inputStream);
        List<Quiz> quizList = new ArrayList<>();
        Quiz quiz = new Quiz();
        try (
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            for(CSVRecord csvRecord : csvParser){
                quiz.setQuestion(csvRecord.get(1));
                quiz.setAnswer(csvRecord.get(0));
                quizList.add(quiz);
            }
        }
        return quizList;
    }

    @Override
    public int printQuestionsAndAnswers(List<Quiz> list){
        int correctAnswerCount = 0;
        for (Quiz quiz : list) {
            String currentQuestion = messageSource.getMessage("current.question", new String[]{" "}, applicationConfig.getLocale());
            System.out.println(currentQuestion + quiz.getQuestion() + ".");
            if((scannerResult().toLowerCase()).equals(quiz.getAnswer().toLowerCase())) {
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

        return new Student(scannerResult(),scannerResult());
    }

    @Override
    public void passTestOrNot(int countCorrectPersonAnswers, int countCorrectPersonForPassTest, Student student) {
        if(countCorrectPersonAnswers >= countCorrectPersonForPassTest){
            String testIsPassed = messageSource.getMessage("test.passed", new String[]{" "}, applicationConfig.getLocale());
            System.out.println(student.getFirstName() + " " + student.getLastName() + testIsPassed);
        }else {
            String testDontPassed = messageSource.getMessage("not.pass", new String[]{" "}, applicationConfig.getLocale());
            System.out.println(student.getFirstName() + " " + student.getLastName() + testDontPassed);
        }
    }

    @Override
    public String scannerResult(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
