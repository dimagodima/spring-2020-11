package otus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import otus.domain.Comment;
import otus.repository.impl.CommentRepositoryJpa;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(CommentRepositoryJpa.class)
public class CommentRepositoryJpaTest {

    public static final Long COMMENT_ID_FIRST = 1L;
    public static final Long COMMENT_ID_SECOND = 2L;
    public static final Long COMMENT_ID_THIRD = 3L;
    public static final String COMMENT_FIRST = "Хорошая книга";
    public static final String COMMENT_SECOND = "Интересная книга";
    public static final String COMMENT_THIRD = "Отличная книга";

    @Autowired
    private CommentRepositoryJpa repository;
    @Autowired
    private TestEntityManager em;

    @Test
    void shouldFindCommentById(){
        Optional<Comment> Comment = repository.findCommentById(COMMENT_ID_FIRST);
        assertThat(Comment).isPresent().get().hasFieldOrPropertyWithValue("comment", COMMENT_FIRST);
    }

    @Test
    void shouldFindCommentByName(){
        List<Comment> Comment = repository.findCommentByName(COMMENT_FIRST);
        assertThat(Comment.get(0).getId()).isEqualTo(COMMENT_ID_FIRST);
    }

    @Test
    void shouldUpdateCommentById(){
        repository.updateCommentById(new Comment(COMMENT_ID_FIRST,COMMENT_THIRD));
        Comment Comment = em.find(Comment.class, COMMENT_ID_FIRST);
        assertThat(Comment.getComment()).isEqualTo(COMMENT_THIRD);
    }

    @Test
    void shouldDeleteCommentById(){
        repository.deleteCommentById(COMMENT_ID_SECOND);
        Comment Comment = em.find(Comment.class, COMMENT_ID_SECOND);
        assertThat(Comment).isNull();
    }

    @Test
    void shouldSaveCommentById(){
        repository.saveComment(new Comment(COMMENT_ID_THIRD,COMMENT_SECOND));
        Comment Comment = em.find(Comment.class, COMMENT_ID_THIRD);
        assertThat(Comment.getComment()).isEqualTo(COMMENT_SECOND);
    }
    
}
