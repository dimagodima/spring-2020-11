package ru.otus.service;

import ru.otus.model.Book;

import java.util.List;

public interface BookService {
    void addBook(String name,long genreId, long authorId);
    void deleteBook(long id);
    Book getBook(long id);
    List<Book> getAllBooks();
    void updateBook(long bookId, String name, long genreId, long authorId);
}
