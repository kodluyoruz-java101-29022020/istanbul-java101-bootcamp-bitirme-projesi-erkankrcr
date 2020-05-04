package org.kodluyoruz.javabootcamp.libraryproject.service.impl;

import org.kodluyoruz.javabootcamp.libraryproject.dao.BookManipulateDAO;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Book;
import org.kodluyoruz.javabootcamp.libraryproject.service.BookManipulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(scopeName = "singleton")
public class BookManipulateServiceImp implements BookManipulateService {
    @Autowired
    @Qualifier("BookManipulateDAOImp")
    private BookManipulateDAO bookManipulateDAO;

    @Override
    public void createBook(Book book) {
        bookManipulateDAO.createBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookManipulateDAO.updateBook(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookManipulateDAO.deleteBook(book);
    }
}
