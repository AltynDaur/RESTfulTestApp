package dao.jpaimpl;


import dao.BookDAO;
import dao.JPA;
import entity.Book;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@JPA
@Stateless
public class JPABookDAO implements BookDAO, Serializable{

    @Inject
    private EntityManager entityManager;

    @Override
    public Book find(int id) {
        return entityManager.find(Book.class,id);
    }

    @Override
    public void create(Book book) {
        entityManager.merge(book);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.getReference(Book.class,id));
    }

    @Override
    public void update(Book book) {
        entityManager.merge(book);
    }
}
