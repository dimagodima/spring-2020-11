package otus.repository;

import otus.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findCommentById(Long id);
    List<Comment> findCommentByName(String name);
    void updateCommentById(Comment comment);
    Comment saveComment(Comment comment);
    void deleteCommentById(Long id);
}
