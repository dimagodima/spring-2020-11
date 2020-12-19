package ru.otus.service;

import ru.otus.domain.Student;

public interface PassExam {
    void passTestOrNot(int countCorrectPersonAnswers, int countCorrectPersonForPassTest, Student student);
}
