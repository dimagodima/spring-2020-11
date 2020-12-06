import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework1.domain.Quiz;
import ru.otus.homework1.service.CsvServiceImpl;
import ru.otus.homework1.service.ICsvService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Service for work with CSV documents")
public class CsvServiceImplTest {

    @Mock
    ICsvService iCsvService;


    @Test
    @DisplayName("print questions and answers")
        public void printQuestionsAndAnswersTest(){

        List<Quiz> quizList = new ArrayList<>();
        Quiz quiz = new Quiz();
        quiz.setQuestion("What is the capital of Russia?");
        quiz.setAnswer("Moscow.");
        quizList.add(quiz);

        iCsvService.printQuestionsAndAnswers(quizList);

    }



}
