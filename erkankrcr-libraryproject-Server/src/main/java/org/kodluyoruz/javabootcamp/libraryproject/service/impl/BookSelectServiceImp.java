package org.kodluyoruz.javabootcamp.libraryproject.service.impl;

import org.kodluyoruz.javabootcamp.libraryproject.annotion.MethodRunningTime;
import org.kodluyoruz.javabootcamp.libraryproject.dao.BookSelectDAO;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Book;
import org.kodluyoruz.javabootcamp.libraryproject.service.BookSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Scope("singleton")
public class BookSelectServiceImp implements BookSelectService {
    @Autowired
    @Qualifier("BookSelectDAOImp")
    private BookSelectDAO bookSelectDAO;

    @Override
    public Book findBookById(UUID id) {
        return bookSelectDAO.findBookById(id);
    }

    @Override
    public List<Book> allBook() {
        return bookSelectDAO.allBook();
    }
}
