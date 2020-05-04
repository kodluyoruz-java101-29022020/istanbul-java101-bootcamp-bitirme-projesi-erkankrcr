package org.kodluyoruz.javabootcamp.libraryproject.dao;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Library;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;

import java.util.List;
import java.util.UUID;

public interface LibrarySelectDAO {
    Library findLibraryById(UUID id);
    List<Library> allLibrary();
    List<Library> userLibrary(User user);
}
