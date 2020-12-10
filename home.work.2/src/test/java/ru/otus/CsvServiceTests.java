package ru.otus;

import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.otus.domain.Quiz;
import ru.otus.service.CsvService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Тесты для сервиса тестирования учеников")
public class CsvServiceTests {

    @Mock
    CsvService csvService;

    @Test
    public void readFileTest() throws IOException {
        csvService.readFile("quiz.csv");
    }

    @Test
    public void printQuestionsAndAnswersTest() throws IOException {
        List<Quiz> list = new ArrayList<>();
        Quiz quiz = new Quiz();
        quiz.setQuestion("Which framework are we learning?");
        quiz.setAnswer("Spring");
        list.add(quiz);
        String answer = IOUtils.toString(new ByteArrayInputStream("Spring".getBytes()), "UTF-8");
        int correctAnswerCount = 0;
        for (Quiz quizz : list) {
            System.out.println("Current question is: " + quiz.getQuestion() + ".");
            if((answer.toLowerCase()).equals(quiz.getAnswer().toLowerCase())) {
                System.out.println("Answer is correct");
                correctAnswerCount++;
            }else {
                System.out.println("Correct answer is: " + quiz.getAnswer() + ".");
            }
        }

        assertEquals(correctAnswerCount,1);
    }


}
