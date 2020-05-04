package org.kodluyoruz.javabootcamp.libraryproject.dao;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.kodluyoruz.javabootcamp.libraryproject.exception.InvalidUserInfoException;

import java.util.List;
import java.util.UUID;

public interface UserSelectDAO {
    User findUserById(UUID id);
    List<User> allUser();
}
