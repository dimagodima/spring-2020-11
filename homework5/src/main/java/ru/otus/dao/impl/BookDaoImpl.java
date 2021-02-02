package ru.otus.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Book;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class BookDaoImpl implements BookDao {

    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    private static final String GET_BOOK_BY_ID = "select books.id, books.genre_id, authors.author_name, genres.genre_name, books.name" +
            " from books join authors on books.author_id = authors.author_id join genres on books.genre_id = genres.genre_id where id = :id;";
    private static final String GET_BOOK_BY_NAME = "select books.id, authors.author_name, genres.genre_name, books.name from books " +
            "join authors on books.author_id = authors.author_id join genres on books.genre_id = genres.genre_id where name = :name limit 10;";
    private static final String ADD_BOOK = "insert into books (name, genre_id, author_id) values (:name, :genre_id, :author_id);";
    private static final String DELETE_BOOK_BY_ID = "delete from books where id = :id;";
    private static final String UPDATE_BOOK_BY_ID = "update books set name = :name, author_id = :author_id, genre_id = :genre_id where id = :id;";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoImpl(AuthorDao authorDao, GenreDao genreDao, NamedParameterJdbcTemplate jdbcTemplate) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book getBookById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        return jdbcTemplate.queryForObject(GET_BOOK_BY_ID, namedParameters,bookRowMapper());
    }

    @Override
    public Book getBookByName(String name) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", name);
        return jdbcTemplate.queryForObject(GET_BOOK_BY_NAME, namedParameters,bookRowMapper());
    }

    @Override
    public void addBook(Book book) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
        .addValue("genre_id", genreDao.addGenre(book.getGenre()))
        .addValue("author_id", authorDao.addAuthor(book.getAuthor()))
        .addValue("name",book.getName());
        jdbcTemplate.update(ADD_BOOK, namedParameters);
    }

    @Override
    public void deleteBookById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update(DELETE_BOOK_BY_ID, namedParameters);
    }

    @Override
    public void updateBookById(Long id, String name, Long authorId, Long genreId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", name)
                .addValue("author_id", authorId)
                .addValue("genre_id", genreId);
        jdbcTemplate.update(UPDATE_BOOK_BY_ID, namedParameters);
    }

    RowMapper<Book> bookRowMapper(){
        return new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int i) throws SQLException {
                Book book = new Book();
                book.setId(rs.getLong("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author_name"));
                book.setGenre(rs.getString("genre_name"));

                return book;
            }
        };
    }
}
