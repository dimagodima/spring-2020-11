package ru.otus.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.otus.dao.GenreDao;

public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String ADD_GENRE = "insert into genres (genre_name) values (:genre_name);";
    private static final String UPDATE_GENRE_BY_ID = "update genres set genre_name = :genre_name where genre_id = :genre_id;";
    private static final String FIND_GENRE_NAME_BY_ID = "select genre_name from genres where genre_id = :genre_id;";

    public GenreDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long addGenre(String genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("genre_name", genre);
        jdbcTemplate.update(ADD_GENRE, namedParameters,
                keyHolder, new String[]{"genre_id"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public void updateGenreById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("genre_id", id);
        jdbcTemplate.update(UPDATE_GENRE_BY_ID, namedParameters);
    }

    @Override
    public String findGenreById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("genre_id", id);
        return jdbcTemplate.queryForObject(FIND_GENRE_NAME_BY_ID, namedParameters,
                String.class);
    }
}
