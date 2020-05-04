package org.kodluyoruz.javabootcamp.libraryproject.dao;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Book;

public interface BookManipulateDAO {
    void createBook(Book book);
    void updateBook(Book book);
    void deleteBook(Book book);
}
