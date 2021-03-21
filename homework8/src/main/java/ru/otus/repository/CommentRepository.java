package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.model.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment,String> {
}
