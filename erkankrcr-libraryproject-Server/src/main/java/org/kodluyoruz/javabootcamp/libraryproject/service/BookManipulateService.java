package org.kodluyoruz.javabootcamp.libraryproject.service;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Book;

public interface BookManipulateService {
    void createBook(Book book);
    void updateBook(Book book);
    void deleteBook(Book book);
}
