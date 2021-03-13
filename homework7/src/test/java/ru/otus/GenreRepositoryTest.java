package ru.otus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.domain.Genre;
import ru.otus.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class GenreRepositoryTest {

    private static final Long GENRE_ID_FIRST = 1L;
    private static final Long GENRE_ID_SECOND = 2L;
    private static final Long GENRE_ID_THIRD = 3L;
    private static final String GENRE_NAME_FIRST = "роман";
    private static final String GENRE_NAME_SECOND = "повесть";
    private static final String GENRE_NAME_THIRD = "поэма";

    @Autowired
    private GenreRepository repository;
    @Autowired
    private TestEntityManager em;

    @Test
    void shouldFindGenreById(){
        Optional<Genre> genre = repository.findById(GENRE_ID_FIRST);
        assertThat(genre).isPresent().get().hasFieldOrPropertyWithValue("name", GENRE_NAME_FIRST);
    }

    @Test
    void shouldFindGenreByName(){
        List<Genre> genre = repository.findByName(GENRE_NAME_FIRST);
        assertThat(genre.get(0).getId()).isEqualTo(GENRE_ID_FIRST);
    }

    @Test
    void shouldUpdateGenreById(){
        repository.updateGenreById(GENRE_NAME_THIRD, GENRE_ID_FIRST);
        Genre genre = em.find(Genre.class, GENRE_ID_FIRST);
        assertThat(genre.getName()).isEqualTo(GENRE_NAME_THIRD);
    }

    @Test
    void shouldSaveGenreById(){
        repository.save(new Genre(GENRE_ID_THIRD,GENRE_NAME_SECOND));
        Genre genre = em.find(Genre.class, GENRE_ID_THIRD);
        assertThat(genre.getName()).isEqualTo(GENRE_NAME_SECOND);
    }

    @Test
    void shouldDeleteGenreById(){
        repository.deleteById(GENRE_ID_SECOND);
        Genre genre = em.find(Genre.class, GENRE_ID_SECOND);
        assertThat(genre).isNull();
    }

}
