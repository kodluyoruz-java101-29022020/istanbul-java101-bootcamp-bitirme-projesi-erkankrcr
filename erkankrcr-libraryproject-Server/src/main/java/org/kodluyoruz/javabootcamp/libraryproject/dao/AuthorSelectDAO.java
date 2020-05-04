package org.kodluyoruz.javabootcamp.libraryproject.dao;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorSelectDAO {
    List<Author> allAuthor();
    Author findAuthorById(UUID id);
}
