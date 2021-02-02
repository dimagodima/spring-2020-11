package ru.otus.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.otus.dao.AuthorDao;

public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final static String ADD_AUTHOR = "insert into authors (author_name) values (:author_name);";
    private final static String UPDATE_AUTHOR_BY_ID = "update authors set author_name = :author_name where author_id = :author_id;";
    private final static String FIND_AUTHOR_NAME_BY_ID = "select author_name from authors where author_id = :author_id;";


    public AuthorDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long addAuthor(String author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("author_name", author);
        jdbcTemplate.update(ADD_AUTHOR, namedParameters,
                keyHolder, new String[]{"author_id"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public void updateAuthorById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("author_id", id);
        jdbcTemplate.update(UPDATE_AUTHOR_BY_ID, namedParameters);
    }

    @Override
    public String findAuthorById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("author_id", id);
        return jdbcTemplate.queryForObject(FIND_AUTHOR_NAME_BY_ID, namedParameters,
                String.class);
    }

}
