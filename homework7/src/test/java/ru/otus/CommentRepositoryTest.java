package ru.otus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.Modifying;
import ru.otus.domain.Comment;
import ru.otus.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CommentRepositoryTest {

    public static final Long COMMENT_ID_FIRST = 1L;
    public static final Long COMMENT_ID_SECOND = 2L;
    public static final Long COMMENT_ID_THIRD = 3L;
    public static final String COMMENT_FIRST = "Хорошая книга";
    public static final String COMMENT_SECOND = "Интересная книга";
    public static final String COMMENT_THIRD = "Отличная книга";

    @Autowired
    private CommentRepository repository;
    @Autowired
    private TestEntityManager em;

    @Test
    void shouldFindCommentById(){
        Optional<Comment> Comment = repository.findById(COMMENT_ID_FIRST);
        assertThat(Comment).isPresent().get().hasFieldOrPropertyWithValue("comment", COMMENT_FIRST);
    }

    @Test
    void shouldFindCommentByName(){
        List<Comment> comment = repository.findByComment(COMMENT_FIRST);
        assertThat(comment.get(0).getId()).isEqualTo(COMMENT_ID_FIRST);
    }

    @Test
    void shouldUpdateCommentById(){
        Comment comment = em.find(Comment.class, COMMENT_ID_FIRST);
        comment.setComment(COMMENT_THIRD);
        repository.save(comment);
        Comment comment1 = em.find(Comment.class, COMMENT_ID_FIRST);
        assertThat(comment1.getComment()).isEqualTo(COMMENT_THIRD);
    }

    @Test
    void shouldDeleteCommentById(){
        repository.deleteById(COMMENT_ID_SECOND);
        Comment Comment = em.find(Comment.class, COMMENT_ID_SECOND);
        assertThat(Comment).isNull();
    }

    @Test
    void shouldSaveCommentById(){
        repository.save(new Comment(COMMENT_ID_THIRD,COMMENT_SECOND));
        Comment comment = em.find(Comment.class, COMMENT_ID_THIRD);
        assertThat(comment.getComment()).isEqualTo(COMMENT_SECOND);
    }
    
}
