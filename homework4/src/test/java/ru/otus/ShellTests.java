package ru.otus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ShellTests {

    @Autowired
    private Shell shell;

    private static final String GREETING = "Welcome to our application, enter command to start exam.";
    private static final String GREETING_COMMAND = "hello";

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void shouldReturnEndMessage(){
        String result = (String) shell.evaluate(()->GREETING_COMMAND);
        assertThat(result).isEqualTo(GREETING);
    }
}
