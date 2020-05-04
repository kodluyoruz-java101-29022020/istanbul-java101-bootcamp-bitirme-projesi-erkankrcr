package org.kodluyoruz.javabootcamp.libraryproject.service.impl;

import org.kodluyoruz.javabootcamp.libraryproject.annotion.MethodRunningTime;
import org.kodluyoruz.javabootcamp.libraryproject.dao.LibrarySelectDAO;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Library;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.kodluyoruz.javabootcamp.libraryproject.service.LibrarySelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Scope("singleton")
public class LibrarySelectServiceImp implements LibrarySelectService {
    @Autowired
    @Qualifier("LibrarySelectDAOImp")
    private LibrarySelectDAO librarySelectDAO;

    @Override
    public Library findLibraryById(UUID id) {
        return librarySelectDAO.findLibraryById(id);
    }

    @Override
    public List<Library> allLibrary() {
        return librarySelectDAO.allLibrary();
    }

    @Override
    public List<Library> userLibrary(User user) {
        return librarySelectDAO.userLibrary(user);
    }

    @Override
    public List<Library> searchLibrary(String query, UUID id) {
        List<Library> libraryList = new ArrayList();
        for (Library library : allLibrary()){
            if (library.getUser().getId().equals(id) && (library.getBooks().getTitle().contains(query))){
                libraryList.add(library);
            }
        }
        return libraryList;
    }
}
