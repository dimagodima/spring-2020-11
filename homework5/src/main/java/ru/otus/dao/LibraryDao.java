package ru.otus.dao;

import ru.otus.domain.Book;

public interface LibraryDao {

    Book getBookById(Long id);
    Book getBookByName(String name);
    void addBook(Book book);
    void deleteBookById(Long id);
    void updateBookById(Long id, String name, Long authorId, Long genreId);
    Long addGenre(String genre);
    Long addAuthor(String author);
    void updateGenreById(Long id);
    void updateAuthorById(Long id);
    String findGenreById(Long id);
    String findAuthorById(Long id);

}
