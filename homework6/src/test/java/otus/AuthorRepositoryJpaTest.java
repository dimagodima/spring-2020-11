package otus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import otus.domain.Author;
import otus.repository.impl.AuthorRepositoryJpa;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



@DataJpaTest
@Import(AuthorRepositoryJpa.class)
public class AuthorRepositoryJpaTest {

    public static final long AUTHOR_ID_FIRST = 1L;
    public static final long AUTHOR_ID_SECOND = 2L;
    public static final long AUTHOR_ID_THIRD = 3L;
    public static final String AUTHOR_NAME_FIRST = "Михаил Булгаков";
    public static final String AUTHOR_NAME_SECOND = "Александр Грибоедов";
    public static final String AUTHOR_NAME_THIRD = "Иван Тургенев";
    @Autowired
    private AuthorRepositoryJpa repository;

    @Autowired
    private TestEntityManager em;

    @Test
    void shouldFindAuthorById(){
        Optional<Author> author = repository.findAuthorById(AUTHOR_ID_FIRST);
        assertThat(author).isPresent().get().hasFieldOrPropertyWithValue("authorName", AUTHOR_NAME_FIRST);
    }

    @Test
    void shouldFindAuthorByName(){
        List<Author> author = repository.findAuthorByName(AUTHOR_NAME_FIRST);
        assertThat(author.get(0).getAuthorId()).isEqualTo(AUTHOR_ID_FIRST);
    }

    @Test
    void shouldUpdateAuthorById(){
        repository.updateAuthorById(new Author(AUTHOR_ID_FIRST,AUTHOR_NAME_SECOND));
        Author author = em.find(Author.class, AUTHOR_ID_FIRST);
        assertThat(author.getAuthorName()).isEqualTo(AUTHOR_NAME_SECOND);
    }

    @Test
    void shouldSaveAuthorById(){
        repository.saveAuthor(new Author(AUTHOR_ID_THIRD,AUTHOR_NAME_THIRD));
        Author author = em.find(Author.class, AUTHOR_ID_THIRD);
        assertThat(author.getAuthorName()).isEqualTo(AUTHOR_NAME_THIRD);
    }

    @Test
    void shouldDeleteAuthorById(){
        Author authorForDelete = new Author(AUTHOR_ID_THIRD,AUTHOR_NAME_THIRD);
        repository.deleteAuthorById(authorForDelete);
        Author authorForCheck = em.find(Author.class, AUTHOR_ID_THIRD);
        assertThat(authorForCheck).isNull();
    }

}
