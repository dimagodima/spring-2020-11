package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findById(Long id);
    List<Book> findByName(String name);
    Book save(Book book);
    void deleteById(Long id);
    List<Book> findAll();

    @Modifying
    @Query("update Book b set b.name = :name where b.id = :id")
    void updateBookNameById(@Param("id") Long id, @Param("name") String name);
}
