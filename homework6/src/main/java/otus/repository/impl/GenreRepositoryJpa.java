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
        em.merge(genre);
    }

    @Override
    public Optional<Genre> findGenreById(Long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
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
    public void deleteGenreById(Genre genre) {
        if(em.contains(genre)){
            em.remove(genre);
        }else{
            em.remove(em.merge(genre));
        }
    }
}
