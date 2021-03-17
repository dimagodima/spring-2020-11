package otus.repository;

import otus.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre saveGenre(Genre genre);
    void updateGenreById(Genre genre);
    Optional<Genre> findGenreById(Long id);
    List<Genre> findGenreByName(String name);
    void deleteGenreById(Genre genre);
}
