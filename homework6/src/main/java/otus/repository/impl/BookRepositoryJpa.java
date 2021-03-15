package otus.repository.impl;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import otus.domain.Book;
import otus.repository.BookRepository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Optional<Book> findBookById(Long id) {
        Optional<Book> book = Optional.ofNullable(em.find(Book.class, id));
        book.ifPresent(value -> Hibernate.initialize(value.getComments()));
        return book;    }

    @Override
    public List<Book> findBookByName(String name) {
        EntityGraph<?> entityGraph = em.getEntityGraph("books-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.author join fetch b.genre join fetch b.comments where b.name = :name", Book.class);
        query.setParameter("name", name);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();

    }

    @Override
    @Transactional
    public Book saveBook(Book book) {
        if(book.getId() <= 0){
            em.persist(book);
            return book;
        }else {
            return em.merge(book);
        }
    }

    @Override
    @Transactional
    public void deleteBookById(Book book) {
        if(em.contains(book)){
            em.remove(book);
        }else{
            em.remove(em.merge(book));
        }
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        em.merge(book);
    }

    @Override
    @Transactional
    public List<Book> findAllBooks() {
        EntityGraph<?> entityGraph = em.getEntityGraph("books-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.author join fetch b.genre join fetch b.comments", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }
}
