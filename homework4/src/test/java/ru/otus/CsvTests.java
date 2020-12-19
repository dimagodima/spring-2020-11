package ru.otus;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.domain.Quiz;
import ru.otus.service.CsvService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CsvTests {

    @MockBean
    private CsvService csvService;

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
        String answer = IOUtils.toString(new ByteArrayInputStream("Spring".getBytes()), StandardCharsets.UTF_8);
        int correctAnswerCount = 0;
        for (Quiz quizz : list) {
            System.out.println("Current question is: " + quizz.getQuestion() + ".");
            if (answer.equalsIgnoreCase(quizz.getAnswer())) {
                System.out.println("Answer is correct");
                correctAnswerCount++;
            } else {
                System.out.println("Correct answer is: " + quizz.getAnswer() + ".");
            }
        }
        assertEquals(correctAnswerCount, 1);
    }

    @Test
    public void askFirstAndLastNamesTest(){

    }

}
