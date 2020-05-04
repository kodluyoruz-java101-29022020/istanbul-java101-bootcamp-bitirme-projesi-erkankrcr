package org.kodluyoruz.javabootcamp.libraryproject.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.kodluyoruz.javabootcamp.libraryproject.annotion.MethodRunningTime;
import org.kodluyoruz.javabootcamp.libraryproject.dao.AuthorSelectDAO;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("AuthorSelectDAOImp")
public class AuthorSelectDAOImp implements AuthorSelectDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Author> allAuthor() {
        Session session = sessionFactory.openSession();

        Query<Author> authorQuery = session.createQuery("select a from Author a",Author.class);

        return authorQuery.getResultList();
    }


    @Override
    public Author findAuthorById(UUID id) {
        Session session = sessionFactory.openSession();

        Query<Author> authorQuery = session.createQuery("select a from Author a where a.id =: id",Author.class);
        authorQuery.setParameter("id",id);

        return authorQuery.getSingleResult();
    }
}
