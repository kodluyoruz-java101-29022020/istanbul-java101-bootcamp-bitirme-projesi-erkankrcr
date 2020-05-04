package org.kodluyoruz.javabootcamp.libraryproject.service;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;

public interface UserManipulateService {
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
