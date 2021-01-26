package ru.otus.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.dao.LibraryDao;
import ru.otus.domain.Book;
import ru.otus.utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class LibraryDaoImpl implements LibraryDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LibraryDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book getBookById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        return jdbcTemplate.queryForObject(Utils.resourceAsString(getClass(), "dao/get_book_by_id.sql"), namedParameters,bookRowMapper());
    }

    @Override
    public Book getBookByName(String name) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", name);
        return jdbcTemplate.queryForObject(Utils.resourceAsString(getClass(), "dao/get_book_by_name.sql"), namedParameters,bookRowMapper());
    }

    @Override
    public void addBook(Book book) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
        .addValue("genre_id", addGenre(book.getGenre()))
        .addValue("author_id", addAuthor(book.getAuthor()))
        .addValue("name",book.getName());
        jdbcTemplate.update(Utils.resourceAsString(getClass(), "dao/add_book.sql"), namedParameters);
    }

    @Override
    public void deleteBookById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update(Utils.resourceAsString(getClass(), "dao/delete_book_by_id.sql"), namedParameters);
    }

    @Override
    public void updateBookById(Long id, String name, Long authorId, Long genreId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", name)
                .addValue("author_id", authorId)
                .addValue("genre_id", genreId);
        jdbcTemplate.update(Utils.resourceAsString(getClass(), "dao/update_book_by_id.sql"), namedParameters);
    }

    @Override
    public Long addGenre(String genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("genre_name", genre);
        jdbcTemplate.update(Utils.resourceAsString(getClass(), "dao/add_genre.sql"), namedParameters,
                keyHolder, new String[]{"genre_id"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public Long addAuthor(String author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("author_name", author);
        jdbcTemplate.update(Utils.resourceAsString(getClass(), "dao/add_author.sql"), namedParameters,
                keyHolder, new String[]{"author_id"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public void updateGenreById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("genre_id", id);
        jdbcTemplate.update(Utils.resourceAsString(getClass(), "dao/update_genre_by_id.sql"), namedParameters);
    }

    @Override
    public void updateAuthorById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("author_id", id);
        jdbcTemplate.update(Utils.resourceAsString(getClass(), "dao/update_author_by_id.sql"), namedParameters);
    }

    @Override
    public String findGenreById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("genre_id", id);
        return jdbcTemplate.queryForObject(Utils.resourceAsString(getClass(), "dao/find_genre_name_by_id.sql"), namedParameters,
                String.class);
    }

    @Override
    public String findAuthorById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("author_id", id);
        return jdbcTemplate.queryForObject(Utils.resourceAsString(getClass(), "dao/find_author_name_by_id.sql"), namedParameters,
                String.class);
    }

    RowMapper<Book> bookRowMapper(){
        return new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int i) throws SQLException {
                Book book = new Book();
                book.setId(rs.getLong("id"));
                book.setName(rs.getString("name"));
                book.setAuthorId(rs.getLong("author_id"));
                book.setGenreId(rs.getLong("genre_id"));

                return book;
            }
        };
    }
}
