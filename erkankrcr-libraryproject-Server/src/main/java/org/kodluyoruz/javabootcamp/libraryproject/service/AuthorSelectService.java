package org.kodluyoruz.javabootcamp.libraryproject.service;

import org.kodluyoruz.javabootcamp.libraryproject.annotion.MethodRunningTime;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorSelectService {
    Author findAuthorById(UUID id);
    List<Author> allAuthor();
}
