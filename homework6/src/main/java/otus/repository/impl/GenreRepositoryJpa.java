package otus.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import otus.domain.Genre;
import otus.repository.GenreRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Genre saveGenre(Genre genre) {
        if(genre.getGenreId() <= 0){
            em.persist(genre);
            return genre;
        }else {
            return em.merge(genre);
        }
    }

    @Override
    @Transactional
    public void updateGenreById(Genre genre) {
        Query query = em.createQuery("update Genre g " +
                "set g.genreName = :name " +
                "where g.genreId = :id");
        query.setParameter("name", genre.getGenreName());
        query.setParameter("id", genre.getGenreName());
        query.executeUpdate();
    }

    @Override
    @Transactional
    public Optional<Genre> findGenreById(Long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    @Transactional
    public List<Genre> findGenreByName(String name) {
        TypedQuery<Genre> query = em.createQuery("select g " +
                        "from Genre g " +
                        "where g.genreName = :name",
                Genre.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteGenreById(Long id) {
        Query query = em.createQuery("delete " +
                "from Genre a " +
                "where a.genreId = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
