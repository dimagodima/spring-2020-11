package ru.otus.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class BookRepositoryTest {

    private static final String BOOK_ID_FIRST = "1";
    private static final String BOOK_NAME_FIRST = "Одиссея";
    public static final String BOOK = "КНИГА";
    public static final String AUTHOR = "АВТОР";
    public static final String GENRE = "ЖАНР";

    @Autowired
    private BookRepository repository;
    @Autowired
    private MongoTemplate template;

    @Test
    public void shouldFindBookById(){
        Optional<Book> bookOptional = repository.findById(BOOK_ID_FIRST);
        assertThat(bookOptional.get().getName()).isEqualTo(BOOK_NAME_FIRST);
    }

    @Test
    public void shouldDeleteBookById(){
        repository.deleteById(BOOK_ID_FIRST);
        List<Book> books = template.findAll(Book.class);
        assertThat(books.size()).isEqualTo(2);
    }

    @Test
    public void shouldInsertBook(){
        repository.save(new Book(BOOK,new Author(AUTHOR), new Genre(GENRE)));
        List<Book> books = template.findAll(Book.class);
        assertThat(books.size()).isEqualTo(4);
    }

}
