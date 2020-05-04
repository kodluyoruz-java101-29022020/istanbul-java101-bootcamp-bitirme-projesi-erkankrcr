package org.kodluyoruz.javabootcamp.libraryproject.service;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Book;

import java.util.List;
import java.util.UUID;

public interface BookSelectService {
    Book findBookById(UUID id);
    List<Book> allBook();
}
