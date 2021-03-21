package ru.otus.service;

import ru.otus.model.Comment;

public interface CommentService {
    void deleteCommentById(String id);
    void addComment(Comment comment);
    void updateCommentById(String id, String comment);
    Comment getCommentById(String id);
}
