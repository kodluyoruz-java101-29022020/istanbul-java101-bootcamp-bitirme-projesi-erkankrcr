package org.kodluyoruz.javabootcamp.libraryproject.dao;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Book;

import java.util.List;
import java.util.UUID;

public interface BookSelectDAO {
    Book findBookById(UUID id);
    List<Book> allBook();
}
