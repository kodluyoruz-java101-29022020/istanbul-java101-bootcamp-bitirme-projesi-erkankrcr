package org.kodluyoruz.javabootcamp.libraryproject.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.kodluyoruz.javabootcamp.libraryproject.dao.UserSelectDAO;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.kodluyoruz.javabootcamp.libraryproject.exception.InvalidUserInfoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("UserSelectDAOImp")
public class UserSelectDAOImp implements UserSelectDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUserById(UUID id) {
        Session session = sessionFactory.openSession();

        Query<User> userQuery = session.createQuery("select u from User u where u.id =:id",User.class);
        userQuery.setParameter("id",id);

        return userQuery.getSingleResult();

    }

    @Override
    public List<User> allUser() {
        Session session = sessionFactory.openSession();

        Query<User> userQuery = session.createQuery("select u from User u",User.class);

        return userQuery.getResultList();
    }
}
