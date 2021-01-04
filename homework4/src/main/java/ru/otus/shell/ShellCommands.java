package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.service.QuizService;


@ShellComponent
public class ShellCommands {

    private final QuizService quizService;

    public ShellCommands(QuizService quizService) {
        this.quizService = quizService;
    }

    @ShellMethod(value = "Let's start exam.", key = {"hello","hi","hola"})
    public String helloCommand(){
        return "Welcome to our application, enter command to start exam.";
    }

    @ShellMethod(value = "Let's start exam.", key = {"start","exam"})
    public String startExamCommand(){
        quizService.startExam();

        return "Testing completed";
    }

}
