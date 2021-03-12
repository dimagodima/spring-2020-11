package otus.repository;

import otus.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Optional<Author> findAuthorById(Long id);
    List<Author> findAuthorByName(String name);
    void updateAuthorById(Author author);
    Author saveAuthor(Author author);
    void deleteAuthorById(Long id);
}
