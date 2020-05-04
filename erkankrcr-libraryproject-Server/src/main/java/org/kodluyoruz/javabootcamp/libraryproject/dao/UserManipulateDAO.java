package org.kodluyoruz.javabootcamp.libraryproject.dao;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;

public interface UserManipulateDAO {
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
