package org.kodluyoruz.javabootcamp.libraryproject.service;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.kodluyoruz.javabootcamp.libraryproject.exception.InvalidUserInfoException;

import java.util.List;
import java.util.UUID;

public interface UserSelectService {
    User findUserById(UUID id) throws InvalidUserInfoException;
    List<User> allUser();
    User findUserByUserNameAndPassword(String username, String password) throws InvalidUserInfoException;
}
