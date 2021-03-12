package otus.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import otus.domain.Comment;
import otus.repository.CommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public Optional<Comment> findCommentById(Long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    @Transactional
    public List<Comment> findCommentByName(String name) {
        TypedQuery<Comment> query = em.createQuery("select c " +
                        "from Comment c " +
                        "where c.comment = :name",
                Comment.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateCommentById(Comment comment) {
        Query query = em.createQuery("update Comment c " +
                "set c.comment = :comment " +
                "where c.id = :id");
        query.setParameter("comment", comment.getComment());
        query.setParameter("id", comment.getId());
        query.executeUpdate();
    }

    @Override
    @Transactional
    public Comment saveComment(Comment comment) {
        if(comment.getId() <= 0){
            em.persist(comment);
            return comment;
        }else {
            return em.merge(comment);
        }
    }

    @Override
    @Transactional
    public void deleteCommentById(Long id) {
        Query query = em.createQuery("delete " +
                "from Comment c " +
                "where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
