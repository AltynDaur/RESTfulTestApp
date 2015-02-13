package dao;


import entity.Book;

public interface BookDAO {
    Book find(int id);
    void create(Book book);
    void delete(int id);
    void update(Book book);
}
