package ru.otus.service;

import ru.otus.model.Author;

public interface AuthorService {
    void deleteAuthorById(String id);
    void addAuthor(Author author);
    Author getAuthorById(String id);
}
