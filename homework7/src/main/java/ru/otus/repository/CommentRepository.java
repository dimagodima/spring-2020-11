package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long id);
    List<Comment> findByComment(String comment);
    Comment save(Comment comment);
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Comment c SET c.comment = :comment WHERE c.id = :id")
    void updateCommentById(@Param("comment") String comment, @Param("id") Long id);
}
