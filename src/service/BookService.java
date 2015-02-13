package service;

import dao.BookDAO;
import dao.JPA;
import entity.Book;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class BookService {

    @Inject
    @JPA
    private BookDAO bookDAO;

    public Book findByID(int id){
        return bookDAO.find(id);
    }

    public void add(Book book){
        bookDAO.create(book);
    }

    public void delete(int id){
        bookDAO.delete(id);
    }

    public void update(Book book){
        bookDAO.update(book);
    }
}
