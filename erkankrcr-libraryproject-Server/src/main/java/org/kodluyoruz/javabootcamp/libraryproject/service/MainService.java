package org.kodluyoruz.javabootcamp.libraryproject.service;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Author;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Library;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.kodluyoruz.javabootcamp.libraryproject.dao.model.LibraryRequestContext;
import org.kodluyoruz.javabootcamp.libraryproject.dao.model.UserRequestContext;
import org.kodluyoruz.javabootcamp.libraryproject.exception.InvalidUserInfoException;

import java.util.List;
import java.util.UUID;

public interface MainService {
    List<Library> getUserLibrary(UUID id) throws InvalidUserInfoException;
    boolean chechUser(UserRequestContext userRequestContext) throws InvalidUserInfoException;
    boolean chechUserById(UUID id) throws InvalidUserInfoException;
    User getUser(UUID id) throws InvalidUserInfoException;
    User getUserByUserNameAndPassword(String username , String password) throws InvalidUserInfoException;
    User createUserByUserContext(UserRequestContext userRequestContext);
    List<Author> getAllAuthor();
    void addBookToLibrary(LibraryRequestContext requestContext,UUID userId) throws InvalidUserInfoException;
    List<Library> searchLibrary(String query,UUID id);
    void changeLibrary(UUID id , UUID userId);
    void deleteUser(UUID id) throws InvalidUserInfoException;
    Library getLibrary(UUID id);

}
