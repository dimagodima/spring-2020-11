package ru.otus.service;

import ru.otus.model.Genre;

public interface GenreService {
    void deleteGenreById(String id);
    void addGenre(Genre genre);
    Genre getGenreById(String id);
}
