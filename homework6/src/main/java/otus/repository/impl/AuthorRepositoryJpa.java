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
    @Transactional
    public Optional<Author> findAuthorById(Long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    @Transactional
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
        Query query = em.createQuery("update Author a " +
                "set a.authorName = :name " +
                "where a.authorId = :id");
        query.setParameter("name", author.getAuthorName());
        query.setParameter("id", author.getAuthorId());
        query.executeUpdate();
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
    public void deleteAuthorById(Long id) {
        Query query = em.createQuery("delete " +
                "from Author a " +
                "where a.authorId = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
