package org.kodluyoruz.javabootcamp.libraryproject.dao.impl;

import org.hibernate.SessionFactory;
import org.kodluyoruz.javabootcamp.libraryproject.dao.UserManipulateDAO;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.kodluyoruz.javabootcamp.libraryproject.util.session.UtilManipulateSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("UserManipulateDAOImp")
public class UserManipulateDAOImp implements UserManipulateDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UtilManipulateSession<User> utilManipulateSession;

    @Override
    public void createUser(User user) {
        utilManipulateSession.create(sessionFactory,user);
    }

    @Override
    public void updateUser(User user) {
        utilManipulateSession.update(sessionFactory,user);
    }

    @Override
    public void deleteUser(User user) {
        utilManipulateSession.delete(sessionFactory,user);
    }
}
