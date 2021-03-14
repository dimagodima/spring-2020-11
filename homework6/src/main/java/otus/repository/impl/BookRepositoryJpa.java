package otus.repository.impl;

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
    public Optional<Book> findBookById(Long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findBookByName(String name) {
        TypedQuery<Book> query = em.createQuery("select b " +
                        "from Book b " +
                        "where b.name = :name",
                Book.class);
        query.setParameter("name", name);
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
    public void updateBookNameById(Book book) {
        em.merge(book);
    }

    @Override
    @Transactional
    public List<Book> findAllBooks() {
        EntityGraph<?> entityGraph = em.getEntityGraph("books-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }
}
