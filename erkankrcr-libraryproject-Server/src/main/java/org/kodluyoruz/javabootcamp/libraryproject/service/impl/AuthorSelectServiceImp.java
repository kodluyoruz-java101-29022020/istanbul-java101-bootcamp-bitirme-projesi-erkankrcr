package org.kodluyoruz.javabootcamp.libraryproject.service.impl;

import org.kodluyoruz.javabootcamp.libraryproject.annotion.MethodRunningTime;
import org.kodluyoruz.javabootcamp.libraryproject.dao.AuthorSelectDAO;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Author;
import org.kodluyoruz.javabootcamp.libraryproject.service.AuthorSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Scope(scopeName = "singleton")
public class AuthorSelectServiceImp implements AuthorSelectService {

    @Autowired
    @Qualifier("AuthorSelectDAOImp")
    private AuthorSelectDAO authorSelectDAO;

    @MethodRunningTime(status = false)
    @Override
    public Author findAuthorById(UUID id) {
        return authorSelectDAO.findAuthorById(id);
    }

    @Override
    public List<Author> allAuthor() {
        return authorSelectDAO.allAuthor();
    }
}
