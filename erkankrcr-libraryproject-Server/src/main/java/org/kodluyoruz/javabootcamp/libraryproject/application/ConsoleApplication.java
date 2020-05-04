package org.kodluyoruz.javabootcamp.libraryproject.application;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Author;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Book;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Library;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.kodluyoruz.javabootcamp.libraryproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ConsoleApplication implements CommandLineRunner {

    @Autowired
    private UserSelectService userSelectService;
    @Autowired
    private UserManipulateService userManipulateService;
    @Autowired
    private LibrarySelectService librarySelectService;
    @Autowired
    private LibraryManipulateService libraryManipulateService;
    @Autowired
    private BookSelectService bookSelectService;
    @Autowired
    private BookManipulateService bookManipulateService;
    @Autowired
    private AuthorSelectService authorSelectService;
    @Autowired
    private AuthorManipulateService authorManipulateService;


    @Override
    public void run(String... args) throws Exception {

        /*
        System.out.println("User Testi");
        User user = userSelectService.findUserById(UUID.fromString("894e1624-2d42-4746-8d3f-c5d3f4eead3c"));
        System.out.println(user);

        System.out.println("Author Testi");
        Author author = authorSelectService.findAuthorById(UUID.fromString("050bdc28-4c96-4bc5-a1b6-5ce65f1898e7"));
        System.out.println(author.getAuthorName()+" "+author.getAuthorLastName());
        System.out.println("Book Testi");
        Book book = bookSelectService.findBookById(UUID.fromString("a5cef883-e8fe-4da7-8ccc-4f392b42bb59"));
        System.out.println(book.getTitle()+" "+book.library.getUser().getId().toString());

        System.out.println("Library Testi");
        Library library = librarySelectService.findLibraryById(UUID.fromString("bd674e1e-8add-4d17-8e05-be77a9225caa"));
        System.out.println(library.toString());

        System.out.println("Library Testi 2");
        List<Library> libraryList = librarySelectService.userLibrary(user);
        for (Library library1 : libraryList){
            System.out.println(library1.toString());
        }

         */

        Library library = librarySelectService.findLibraryById(UUID.fromString("c8393d5c-ddd1-4f21-921a-95a3eb69faf9"));
        libraryManipulateService.deleteLibrary(library);
    }
}
