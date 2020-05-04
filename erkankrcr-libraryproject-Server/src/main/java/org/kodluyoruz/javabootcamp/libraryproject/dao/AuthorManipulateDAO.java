package org.kodluyoruz.javabootcamp.libraryproject.dao;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Author;

public interface AuthorManipulateDAO {
    void deleteAuthor(Author author);
    void updateAuthor(Author author);
    void createAuthor(Author author);
}
