package ru.otus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.otus.service.ICsvService;
import java.io.IOException;

@DisplayName("Тесты для сервиса тестирования учеников")
public class CsvServiceTests {




    @Mock
    ICsvService csvService;

    @Test
    public void readFileTest() throws IOException {

    csvService.readFile("quiz.csv");


    }

}
