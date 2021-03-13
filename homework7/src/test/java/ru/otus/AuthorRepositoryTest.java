package ru.otus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.domain.Author;
import ru.otus.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AuthorRepositoryTest {

    public static final long AUTHOR_ID_FIRST = 1L;
    public static final long AUTHOR_ID_SECOND = 2L;
    public static final long AUTHOR_ID_THIRD = 3L;
    public static final String AUTHOR_NAME_FIRST = "Михаил Булгаков";
    public static final String AUTHOR_NAME_SECOND = "Александр Грибоедов";
    public static final String AUTHOR_NAME_THIRD = "Иван Тургенев";

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    void shouldFindAuthorById(){
        Optional<Author> author = repository.findById(AUTHOR_ID_FIRST);
        assertThat(author).isPresent().get().hasFieldOrPropertyWithValue("name", AUTHOR_NAME_FIRST);
    }

    @Test
    void shouldFindAuthorByName(){
        List<Author> author = repository.findByName(AUTHOR_NAME_FIRST);
        assertThat(author.get(0).getId()).isEqualTo(AUTHOR_ID_FIRST);
    }

    @Test
    void shouldUpdateAuthorById(){
        repository.updateAuthorById(AUTHOR_NAME_SECOND,AUTHOR_ID_FIRST);
        Author author = em.find(Author.class, AUTHOR_ID_FIRST);
        assertThat(author.getName()).isEqualTo(AUTHOR_NAME_SECOND);
    }

    @Test
    void shouldSaveAuthorById(){
        repository.save(new Author(AUTHOR_ID_THIRD,AUTHOR_NAME_THIRD));
        Author author = em.find(Author.class, AUTHOR_ID_THIRD);
        assertThat(author.getName()).isEqualTo(AUTHOR_NAME_THIRD);
    }

    @Test
    void shouldDeleteAuthorById(){
        repository.deleteById(AUTHOR_ID_SECOND);
        Author author = em.find(Author.class, AUTHOR_ID_SECOND);
        assertThat(author).isNull();
    }

}
