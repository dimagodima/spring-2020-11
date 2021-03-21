package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book,String> {
}
