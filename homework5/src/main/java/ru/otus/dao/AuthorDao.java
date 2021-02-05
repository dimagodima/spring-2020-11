package ru.otus.dao;

public interface AuthorDao {
    String findAuthorById(Long id);
    void updateAuthorById(Long id);
    Long addAuthor(String author);
}
