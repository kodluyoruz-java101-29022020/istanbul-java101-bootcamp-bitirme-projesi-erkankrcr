package org.kodluyoruz.javabootcamp.libraryproject.dao;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Library;

public interface LibraryManipulateDAO {
    void createLibrary(Library library);
    void updateLibrary(Library library);
    void deleteLibrary(Library library);
}
