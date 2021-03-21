package ru.otus.service;

import ru.otus.model.Book;

public interface BookService {
    void deleteBookById(String id);
    void addBook(Book book);
    void updateBookById(String bookId, String name, String genreId, String authorId);
    Book getBookById(String id);
}
