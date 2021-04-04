package ru.otus.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.model.Comment;
import ru.otus.repository.CommentRepository;
import ru.otus.service.CommentService;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional
    public void deleteCommentById(String id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void addComment(Comment comment) {
        repository.save(comment);
    }

    @Override
    @Transactional
    public void updateCommentById(String id, String comment) {
        Optional<Comment> foundComment = repository.findById(id);
        if(foundComment.isPresent()){
            repository.save(new Comment(id,comment));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getCommentById(String id) {
        return repository.findById(id).get();
    }
}
