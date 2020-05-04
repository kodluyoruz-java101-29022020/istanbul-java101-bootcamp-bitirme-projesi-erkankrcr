package org.kodluyoruz.javabootcamp.libraryproject.dao.impl;

import org.hibernate.SessionFactory;
import org.kodluyoruz.javabootcamp.libraryproject.dao.BookManipulateDAO;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Book;
import org.kodluyoruz.javabootcamp.libraryproject.util.session.UtilManipulateSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("BookManipulateDAOImp")
public class BookManipulateDAOImp implements BookManipulateDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UtilManipulateSession<Book> utilManipulateSession;

    @Override
    public void createBook(Book book) {
        utilManipulateSession.create(sessionFactory,book);
    }

    @Override
    public void updateBook(Book book) {
        utilManipulateSession.update(sessionFactory,book);
    }

    @Override
    public void deleteBook(Book book) {
        utilManipulateSession.delete(sessionFactory,book);
    }
}
