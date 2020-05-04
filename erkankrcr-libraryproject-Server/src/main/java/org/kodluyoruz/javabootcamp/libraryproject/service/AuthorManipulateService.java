package org.kodluyoruz.javabootcamp.libraryproject.service;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Author;

public interface AuthorManipulateService {
    void createAuthor(Author author);
    void updateAuthor(Author author);
    void deleteAuthor(Author author);
}
