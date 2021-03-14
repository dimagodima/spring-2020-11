package otus.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import otus.domain.Author;
import otus.repository.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Author> findAuthorById(Long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> findAuthorByName(String name) {
        TypedQuery<Author> query = em.createQuery("select a " +
                        "from Author a " +
                        "where a.authorName = :name",
                Author.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateAuthorById(Author author) {
        em.merge(author);
    }

    @Override
    @Transactional
    public Author saveAuthor(Author author) {
        if(author.getAuthorId() <= 0){
            em.persist(author);
            return author;
        }else {
            return em.merge(author);
        }
    }

    @Override
    @Transactional
    public void deleteAuthorById(Author author) {
        if(em.contains(author)){
            em.remove(author);
        }else{
            em.remove(em.merge(author));
        }
    }
}
