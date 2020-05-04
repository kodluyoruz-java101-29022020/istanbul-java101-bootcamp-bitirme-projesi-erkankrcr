package org.kodluyoruz.javabootcamp.libraryproject.service.impl;

import org.kodluyoruz.javabootcamp.libraryproject.dao.LibraryManipulateDAO;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Library;
import org.kodluyoruz.javabootcamp.libraryproject.service.LibraryManipulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class LibraryManipulateServiceImp implements LibraryManipulateService {
    @Autowired
    @Qualifier("LibraryManipulateDAOImp")
    private LibraryManipulateDAO libraryManipulateDAO;

    @Override
    public void createLibrary(Library library) {
        libraryManipulateDAO.createLibrary(library);
    }

    @Override
    public void updateLibrary(Library library) {
        libraryManipulateDAO.updateLibrary(library);
    }

    @Override
    public void deleteLibrary(Library library) {
        libraryManipulateDAO.deleteLibrary(library);
    }
}
