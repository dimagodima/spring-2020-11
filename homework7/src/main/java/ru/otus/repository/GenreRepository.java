package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre save(Genre genre);
    Optional<Genre> findById(Long id);
    List<Genre> findByName(String name);
    void deleteById(Long id);

    @Modifying
    @Query("update Genre g set g.name = :name where g.id = :id")
    void updateGenreById(@Param("name") String name, @Param("id") Long id);


}
