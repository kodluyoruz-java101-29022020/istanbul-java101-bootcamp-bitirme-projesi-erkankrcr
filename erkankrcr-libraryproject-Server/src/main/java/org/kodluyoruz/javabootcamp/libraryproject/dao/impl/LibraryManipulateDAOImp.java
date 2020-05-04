package org.kodluyoruz.javabootcamp.libraryproject.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.kodluyoruz.javabootcamp.libraryproject.dao.LibraryManipulateDAO;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Library;
import org.kodluyoruz.javabootcamp.libraryproject.util.session.UtilManipulateSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("LibraryManipulateDAOImp")
public class LibraryManipulateDAOImp implements LibraryManipulateDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UtilManipulateSession<Library> utilManipulateSession;

    @Override
    public void createLibrary(Library library) {
        utilManipulateSession.create(sessionFactory,library);
    }

    @Override
    public void updateLibrary(Library library) {
        utilManipulateSession.update(sessionFactory,library);
    }

    @Override
    public void deleteLibrary(Library library) {
        utilManipulateSession.delete(sessionFactory,library);
    }
}
