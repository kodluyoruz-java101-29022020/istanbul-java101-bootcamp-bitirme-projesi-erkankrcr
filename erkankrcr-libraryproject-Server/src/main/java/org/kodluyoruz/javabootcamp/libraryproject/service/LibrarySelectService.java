package org.kodluyoruz.javabootcamp.libraryproject.service;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Library;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;

import java.util.List;
import java.util.UUID;

public interface LibrarySelectService {
    Library findLibraryById(UUID id);
    List<Library> allLibrary();
    List<Library> userLibrary(User user);
    List<Library> searchLibrary(String query, UUID id);
}
