package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findById(Long id);
    List<Author> findByName(String name);
    Author save(Author author);
    void deleteById(Long id);

    @Modifying
    @Query("update Author a set a.name = :name where a.id = :id")
    void updateAuthorById(@Param("name") String name, @Param("id") Long id);
}