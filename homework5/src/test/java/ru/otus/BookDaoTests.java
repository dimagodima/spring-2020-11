package ru.otus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.dao.impl.AuthorDaoImpl;
import ru.otus.dao.impl.BookDaoImpl;
import ru.otus.dao.impl.GenreDaoImpl;
import ru.otus.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Sql({"schema.sql", "test_data.sql"})
public class BookDaoTests {

    public static final String SELECT_COUNT_FROM_BOOKS = "select count(id) from books";
    public static final String DOSTOEVSKY = "Достоевский Ф.М.";
    public static final String P_AND_N = "Преступление и наказание";
    public static final String BULGAKOV = "Михаил Булгаков";
    public static final String MAS_AND_MAR = "Мастер и Маргарита";
    public static final String CRIME = "Преступление и наказание";

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @MockBean
    private AuthorDao authorDao;
    @MockBean
    private GenreDao genreDao;


    @Test
    public void returnCorrectBookNameById(){
        BookDaoImpl bookDao = new BookDaoImpl(authorDao, genreDao, namedParameterJdbcTemplate);
        assertThat(bookDao.getBookById(1L).getName()).isEqualTo(MAS_AND_MAR);
    }

    @Test
    public void returnCorrectCountOfBooksAfterAddNew(){
        AuthorDaoImpl authorDao = new AuthorDaoImpl(namedParameterJdbcTemplate);
        GenreDaoImpl genreDao = new GenreDaoImpl(namedParameterJdbcTemplate);
        BookDaoImpl bookDao = new BookDaoImpl(authorDao, genreDao, namedParameterJdbcTemplate);
        bookDao.addBook(new Book(2L,"роман", DOSTOEVSKY, CRIME));
        Integer count = namedParameterJdbcTemplate.getJdbcOperations().queryForObject(SELECT_COUNT_FROM_BOOKS, Integer.class);
        assertThat(count).isEqualTo(2);
    }

    @Test
    public void returnCorrectBookAfterUpdate(){
        BookDaoImpl bookDao = new BookDaoImpl(authorDao, genreDao, namedParameterJdbcTemplate);
        bookDao.updateBookById(1L, P_AND_N, 1L,1L);
        Book book = bookDao.getBookById(1L);
        assertThat(book.getName()).isEqualTo(P_AND_N);
        assertThat(book.getAuthor()).isEqualTo(BULGAKOV);
    }

    @Test
    public void returnCorrectCountOfBookAfterDelete(){
        BookDaoImpl bookDao = new BookDaoImpl(authorDao, genreDao, namedParameterJdbcTemplate);
        bookDao.deleteBookById(1L);
        Integer count = namedParameterJdbcTemplate.getJdbcOperations().queryForObject(SELECT_COUNT_FROM_BOOKS, Integer.class);
        assertThat(count).isEqualTo(0);
    }
}
