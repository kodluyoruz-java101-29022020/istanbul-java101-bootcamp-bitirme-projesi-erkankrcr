package org.kodluyoruz.javabootcamp.libraryproject.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.kodluyoruz.javabootcamp.libraryproject.dao.LibrarySelectDAO;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Library;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("LibrarySelectDAOImp")
public class LibrarySelectDAOImp implements LibrarySelectDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Library findLibraryById(UUID id) {
        Session session = sessionFactory.openSession();
        Query<Library> libraryQuery = session.createQuery("select l from Library l where l.id =:id",Library.class);
        libraryQuery.setParameter("id",id);
        return libraryQuery.getSingleResult();
    }

    @Override
    public List<Library> allLibrary() {
        Session session = sessionFactory.openSession();
        Query<Library> libraryQuery = session.createQuery("select l from Library l",Library.class);
        return libraryQuery.getResultList();
    }

    @Override
    public List<Library> userLibrary(User user) {
        Session session = sessionFactory.openSession();
        Query<Library> libraryQuery = session.createQuery("select l from Library l where l.user.id =:id",Library.class);
        libraryQuery.setParameter("id",user.getId());
        return libraryQuery.getResultList();
    }
}
