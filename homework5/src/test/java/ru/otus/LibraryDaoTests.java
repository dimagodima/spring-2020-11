package ru.otus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.dao.impl.LibraryDaoImpl;
import ru.otus.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Sql({"schema.sql", "test_data.sql"})
public class LibraryDaoTests {

    public static final String SELECT_COUNT_FROM_BOOKS = "select count(*) from books";
    public static final String DOSTOEVSKY = "Достоевский Ф.М.";
    public static final String P_AND_N = "Преступление и наказание";

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void returnCorrectBookNameById(){
        LibraryDaoImpl libraryDao = new LibraryDaoImpl(namedParameterJdbcTemplate);
        assertThat(libraryDao.getBookById(1L).getName()).isEqualTo("Мастер и Маргарита");
    }

    @Test
    public void returnCorrectCountOfBooksAfterAddNew(){
        LibraryDaoImpl libraryDao = new LibraryDaoImpl(namedParameterJdbcTemplate);
        libraryDao.addBook(new Book(2L,"роман", DOSTOEVSKY,"Преступление и наказание"));
        Integer count = namedParameterJdbcTemplate.getJdbcOperations().queryForObject(SELECT_COUNT_FROM_BOOKS, Integer.class);
        assertThat(count).isEqualTo(2);
    }

    @Test
    public void returnCorrectBookAfterUpdate(){
        LibraryDaoImpl libraryDao = new LibraryDaoImpl(namedParameterJdbcTemplate);
        libraryDao.updateBookById(1L, P_AND_N,libraryDao.addAuthor(DOSTOEVSKY),1L);
        Book book = libraryDao.getBookById(1L);
        assertThat(book.getName()).isEqualTo(P_AND_N);
        assertThat(book.getAuthorId()).isEqualTo(2L);
    }

    @Test
    public void returnCorrectCountOfBookAfterDelete(){
        LibraryDaoImpl libraryDao = new LibraryDaoImpl(namedParameterJdbcTemplate);
        libraryDao.deleteBookById(1L);
        Integer count = namedParameterJdbcTemplate.getJdbcOperations().queryForObject(SELECT_COUNT_FROM_BOOKS, Integer.class);
        assertThat(count).isEqualTo(0);
    }
}
