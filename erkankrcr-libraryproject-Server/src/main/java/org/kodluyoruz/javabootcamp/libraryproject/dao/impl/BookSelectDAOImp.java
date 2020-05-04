package org.kodluyoruz.javabootcamp.libraryproject.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.kodluyoruz.javabootcamp.libraryproject.dao.BookSelectDAO;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("BookSelectDAOImp")
public class BookSelectDAOImp implements BookSelectDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Book findBookById(UUID id) {
        Session session = sessionFactory.openSession();

        Query<Book> bookQuery = session.createQuery("select b from Book b where b.id =:id",Book.class);
        bookQuery.setParameter("id",id);

        return bookQuery.getSingleResult();
    }

    @Override
    public List<Book> allBook() {
        Session session = sessionFactory.openSession();

        Query<Book> bookQuery = session.createQuery("select b from Book b",Book.class);

        return bookQuery.getResultList();
    }
}
