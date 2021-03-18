package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.model.Comment;

public interface CommentRepository extends MongoRepository<Comment,String> {
}
