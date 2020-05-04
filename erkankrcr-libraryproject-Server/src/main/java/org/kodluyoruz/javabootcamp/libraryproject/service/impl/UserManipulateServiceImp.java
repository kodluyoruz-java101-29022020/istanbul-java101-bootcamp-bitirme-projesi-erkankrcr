package org.kodluyoruz.javabootcamp.libraryproject.service.impl;

import org.kodluyoruz.javabootcamp.libraryproject.dao.UserManipulateDAO;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.kodluyoruz.javabootcamp.libraryproject.service.UserManipulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class UserManipulateServiceImp implements UserManipulateService {
    @Autowired
    @Qualifier("UserManipulateDAOImp")
    private UserManipulateDAO userManipulateDAO;

    @Override
    public void createUser(User user) {
        userManipulateDAO.createUser(user);
    }

    @Override
    public void updateUser(User user) {
        userManipulateDAO.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userManipulateDAO.deleteUser(user);
    }
}
