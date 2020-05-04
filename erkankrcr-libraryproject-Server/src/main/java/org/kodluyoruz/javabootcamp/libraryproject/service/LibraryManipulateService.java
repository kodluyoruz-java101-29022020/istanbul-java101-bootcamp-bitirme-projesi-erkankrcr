package org.kodluyoruz.javabootcamp.libraryproject.service;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Library;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;

public interface LibraryManipulateService {
    void createLibrary(Library library);
    void updateLibrary(Library library);
    void deleteLibrary(Library library);
}
