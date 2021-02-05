package ru.otus.dao;

public interface GenreDao {
    Long addGenre(String genre);
    void updateGenreById(Long id);
    String findGenreById(Long id);
}
