package ru.otus.dao;

import ru.otus.domain.Book;

public interface BookDao {

    Book getBookById(Long id);
    Book getBookByName(String name);
    void addBook(Book book);
    void deleteBookById(Long id);
    void updateBookById(Long id, String name, Long authorId, Long genreId);
}
