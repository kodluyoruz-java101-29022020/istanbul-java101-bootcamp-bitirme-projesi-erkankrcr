package org.kodluyoruz.javabootcamp.libraryproject.service.impl;

import org.kodluyoruz.javabootcamp.libraryproject.dao.AuthorManipulateDAO;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Author;
import org.kodluyoruz.javabootcamp.libraryproject.service.AuthorManipulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "singleton")
public class AuthorManipulateServiceImp implements AuthorManipulateService {

    @Autowired
    @Qualifier("AuthorManipulateDAOImp")
    private AuthorManipulateDAO authorManipulateDAO;


    @Override
    public void createAuthor(Author author) {
        authorManipulateDAO.createAuthor(author);
    }

    @Override
    public void updateAuthor(Author author) {
        authorManipulateDAO.updateAuthor(author);
    }

    @Override
    public void deleteAuthor(Author author) {
        authorManipulateDAO.deleteAuthor(author);
    }
}
