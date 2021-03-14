package otus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import otus.domain.Author;
import otus.domain.Book;
import otus.domain.Comment;
import otus.domain.Genre;
import otus.repository.impl.BookRepositoryJpa;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import(BookRepositoryJpa.class)
public class BookRepositoryJpaTest {

    private static final Long BOOK_ID_FIRST = 1L;
    private static final Long BOOK_ID_SECOND = 2L;
    private static final String BOOK_NAME_FIRST = "Мастер и Маргарита";
    private static final String BOOK_NAME_SECOND = "Вредные советы";
    private static final String BOOK_GENRE = "стихи";
    private static final String BOOK_AUTHOR = "Григорий Остер";
    private static final String BOOK_COMMENT = "Забавная книга";

    @Autowired
    private BookRepositoryJpa repository;

    @Autowired
    private TestEntityManager em;

    @Test
    void shouldFindAllBooks(){
        List<Book> allBooks = repository.findAllBooks();
        assertThat(allBooks.size()).isEqualTo(1);
    }

    @Test
    void shouldFindBookById(){
        Optional<Book> book = repository.findBookById(BOOK_ID_FIRST);
        assertThat(book).isPresent().get().hasFieldOrPropertyWithValue("name",BOOK_NAME_FIRST);
    }

    @Test
    void shouldFindBookByName(){
        List<Book> book = repository.findBookByName(BOOK_NAME_FIRST);
        assertThat(book.get(0).getId()).isEqualTo(BOOK_ID_FIRST);
    }

    @Test
    void shouldSaveBook(){
        repository.saveBook(new Book(BOOK_ID_SECOND,BOOK_NAME_SECOND,new Genre(0L,BOOK_GENRE),
                new Author(0L,BOOK_AUTHOR),
                List.of(new Comment(0L,BOOK_COMMENT))));
        Book book = em.find(Book.class, BOOK_ID_SECOND);
        assertThat(book.getName()).isEqualTo(BOOK_NAME_SECOND);
    }

    @Test
    void shouldUpdateBookNameById(){
        Book bookForUpdate = em.find(Book.class, BOOK_ID_FIRST);
        repository.updateBookNameById(new Book(BOOK_ID_FIRST,BOOK_NAME_SECOND, bookForUpdate.getGenre(), bookForUpdate.getAuthor(),bookForUpdate.getComments()));
        Book bookForCheck = em.find(Book.class, BOOK_ID_FIRST);
        assertThat(bookForCheck.getName()).isEqualTo(BOOK_NAME_SECOND);
    }
    @Test
    void shouldDeleteBookById(){
        repository.deleteBookById(new Book(BOOK_ID_FIRST, BOOK_NAME_FIRST,null,null,null));
        Book book = em.find(Book.class, BOOK_ID_FIRST);
        assertThat(book).isNull();
    }
}
