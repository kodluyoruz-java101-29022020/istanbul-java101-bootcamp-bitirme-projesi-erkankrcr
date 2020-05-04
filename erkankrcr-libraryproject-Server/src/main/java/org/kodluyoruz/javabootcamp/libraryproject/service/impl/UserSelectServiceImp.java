package org.kodluyoruz.javabootcamp.libraryproject.service.impl;

import org.kodluyoruz.javabootcamp.libraryproject.annotion.MethodRunningTime;
import org.kodluyoruz.javabootcamp.libraryproject.dao.UserSelectDAO;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.kodluyoruz.javabootcamp.libraryproject.exception.InvalidUserInfoException;
import org.kodluyoruz.javabootcamp.libraryproject.service.UserSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Scope("singleton")
public class UserSelectServiceImp implements UserSelectService {
    @Autowired
    @Qualifier("UserSelectDAOImp")
    private UserSelectDAO userSelectDAO;

    @Override
    public User findUserById(UUID id) {
        return userSelectDAO.findUserById(id);
    }

    @Override
    public List<User> allUser() {
        return userSelectDAO.allUser();
    }

    @Override
    public User findUserByUserNameAndPassword(String username, String password) throws InvalidUserInfoException {
        for (User user: allUser()){
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                return user;
            }
        }
        throw new InvalidUserInfoException("İlgili kullanıcı bulunamadı");
    }
}
