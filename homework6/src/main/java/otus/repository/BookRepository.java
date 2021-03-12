package otus.repository;

import otus.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> findBookById(Long id);
    List<Book> findBookByName(String name);
    Book saveBook(Book book);
    void deleteBookById(Long id);
    void updateBookNameById(Long id ,String name);
    List<Book> findAllBooks();
}
