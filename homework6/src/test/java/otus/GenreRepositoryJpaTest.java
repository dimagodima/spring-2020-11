package otus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import otus.domain.Genre;
import otus.repository.impl.GenreRepositoryJpa;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(GenreRepositoryJpa.class)
public class GenreRepositoryJpaTest {

    private static final Long GENRE_ID_FIRST = 1L;
    private static final Long GENRE_ID_SECOND = 2L;
    private static final Long GENRE_ID_THIRD = 3L;
    private static final String GENRE_NAME_FIRST = "роман";
    private static final String GENRE_NAME_SECOND = "повесть";
    private static final String GENRE_NAME_THIRD = "поэма";

    @Autowired
    private GenreRepositoryJpa repository;
    @Autowired
    private TestEntityManager em;

    @Test
    void shouldFindGenreById(){
        Optional<Genre> genre = repository.findGenreById(GENRE_ID_FIRST);
        assertThat(genre).isPresent().get().hasFieldOrPropertyWithValue("genreName", GENRE_NAME_FIRST);
    }

    @Test
    void shouldFindGenreByName(){
        List<Genre> genre = repository.findGenreByName(GENRE_NAME_FIRST);
        assertThat(genre.get(0).getGenreId()).isEqualTo(GENRE_ID_FIRST);
    }

    @Test
    void shouldUpdateGenreById(){
        repository.updateGenreById(new Genre(GENRE_ID_FIRST,GENRE_NAME_THIRD));
        Genre genre = em.find(Genre.class, GENRE_ID_FIRST);
        assertThat(genre.getGenreName()).isEqualTo(GENRE_NAME_THIRD);
    }

    @Test
    void shouldSaveGenreById(){
        repository.saveGenre(new Genre(GENRE_ID_THIRD,GENRE_NAME_SECOND));
        Genre genre = em.find(Genre.class, GENRE_ID_THIRD);
        assertThat(genre.getGenreName()).isEqualTo(GENRE_NAME_SECOND);
    }

    @Test
    void shouldDeleteGenreById(){
        repository.deleteGenreById(GENRE_ID_SECOND);
        Genre genre = em.find(Genre.class, GENRE_ID_SECOND);
        assertThat(genre).isNull();
    }

}
