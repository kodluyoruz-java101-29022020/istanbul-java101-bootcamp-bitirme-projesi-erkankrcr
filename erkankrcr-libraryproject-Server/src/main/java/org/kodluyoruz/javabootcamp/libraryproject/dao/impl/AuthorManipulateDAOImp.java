package org.kodluyoruz.javabootcamp.libraryproject.dao.impl;

import org.hibernate.SessionFactory;
import org.kodluyoruz.javabootcamp.libraryproject.dao.AuthorManipulateDAO;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Author;
import org.kodluyoruz.javabootcamp.libraryproject.util.session.UtilManipulateSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("AuthorManipulateDAOImp")
public class AuthorManipulateDAOImp implements AuthorManipulateDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UtilManipulateSession<Author> utilManipulateSession;

    @Override
    public void deleteAuthor(Author author) {
        utilManipulateSession.delete(sessionFactory,author);
    }

    @Override
    public void updateAuthor(Author author) {
        utilManipulateSession.update(sessionFactory,author);
    }

    @Override
    public void createAuthor(Author author) {
        utilManipulateSession.create(sessionFactory,author);
    }
}
