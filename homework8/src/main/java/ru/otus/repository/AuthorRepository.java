package ru.otus.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.model.Author;


public interface AuthorRepository extends MongoRepository<Author,String> {
}
