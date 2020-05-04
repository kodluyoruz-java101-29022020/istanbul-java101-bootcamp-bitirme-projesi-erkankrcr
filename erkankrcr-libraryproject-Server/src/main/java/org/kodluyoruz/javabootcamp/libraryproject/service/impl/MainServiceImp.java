package org.kodluyoruz.javabootcamp.libraryproject.service.impl;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Author;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Book;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Library;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.kodluyoruz.javabootcamp.libraryproject.dao.model.LibraryRequestContext;
import org.kodluyoruz.javabootcamp.libraryproject.dao.model.UserRequestContext;
import org.kodluyoruz.javabootcamp.libraryproject.exception.InvalidUserInfoException;
import org.kodluyoruz.javabootcamp.libraryproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Scope("singleton")
public class MainServiceImp implements MainService {
    @Autowired
    private LibrarySelectService librarySelectService;
    @Autowired
    private LibraryManipulateService libraryManipulateService;
    @Autowired
    private UserSelectService userSelectService;
    @Autowired
    private UserManipulateService userManipulateService;
    @Autowired
    private BookSelectService bookSelectService;
    @Autowired
    private BookManipulateService bookManipulateService;
    @Autowired
    private AuthorSelectService authorSelectService;
    @Autowired
    private AuthorManipulateService authorManipulateService;

    @Override
    public List<Library> getUserLibrary(UUID id) throws InvalidUserInfoException {
        User user = userSelectService.findUserById(id);
        return librarySelectService.userLibrary(user);
    }

    @Override
    public boolean chechUser(UserRequestContext userRequestContext) {
        for (User user : userSelectService.allUser()){
            if (userRequestContext.getUsername().equals(user.getUsername())
                    && userRequestContext.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean chechUserById(UUID id) {
        for (User user : userSelectService.allUser()){
            if (id.equals(user.getId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUser(UUID id) throws InvalidUserInfoException {
        return userSelectService.findUserById(id);
    }

    @Override
    public User getUserByUserNameAndPassword(String username, String password) throws InvalidUserInfoException {
        return userSelectService.findUserByUserNameAndPassword(username,password);
    }

    @Override
    public User createUserByUserContext(UserRequestContext userRequestContext) {
        User tempUser =
                new User(userRequestContext.getUsername(),
                        userRequestContext.getPassword(),
                        userRequestContext.getName(),
                        userRequestContext.getLastname());
        userManipulateService.createUser(tempUser);
        return tempUser;
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorSelectService.allAuthor();
    }

    @Override
    public void addBookToLibrary(LibraryRequestContext requestContext,UUID userId) throws InvalidUserInfoException {
        Author author = authorSelectService.findAuthorById(UUID.fromString(requestContext.getAuthorId()));
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(requestContext.getPublicationYear());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Book book = new Book(
                requestContext.getTitle(),
                requestContext.getDescription(),
                requestContext.getComment(),
                author,date);
        User user = userSelectService.findUserById(userId);
        bookManipulateService.createBook(book);
        Date readStart = Date.from(LocalDate.now().atStartOfDay(ZoneId.of("UTC-8")).toInstant());
        Library library = new Library(readStart,null,readStart,false,user,book);
        libraryManipulateService.createLibrary(library);
        book.library = library;
        bookManipulateService.updateBook(book);
    }

    @Override
    public List<Library> searchLibrary(String query, UUID id) {
        return librarySelectService.searchLibrary(query,id);
    }

    @Override
    public void changeLibrary(UUID id, UUID userId) {
        for (Library library : librarySelectService.allLibrary()){
            if (library.getId().equals(id) && library.getUser().getId().equals(userId)){
                if (library.getComplete()){
                    library.setComplete(false);
                    library.setReadingEnd(null);
                    libraryManipulateService.updateLibrary(library);
                }else{
                    library.setComplete(true);
                    library.setReadingEnd(Date.from(LocalDate.now().atStartOfDay(ZoneId.of("UTC-8")).toInstant()));
                    libraryManipulateService.updateLibrary(library);
                }
            }
        }
    }

    @Override
    public void deleteUser(UUID id) throws InvalidUserInfoException {
        userManipulateService.deleteUser(userSelectService.findUserById(id));
    }

    @Override
    public Library getLibrary(UUID id) {
        return librarySelectService.findLibraryById(id);
    }
}
