package org.kodluyoruz.javabootcamp.libraryproject.controller;

import net.bytebuddy.asm.Advice;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Author;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Book;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Library;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.kodluyoruz.javabootcamp.libraryproject.dao.model.LibraryRequestContext;
import org.kodluyoruz.javabootcamp.libraryproject.dao.model.Message;
import org.kodluyoruz.javabootcamp.libraryproject.exception.InvalidAPIKeyException;
import org.kodluyoruz.javabootcamp.libraryproject.exception.InvalidUserInfoException;
import org.kodluyoruz.javabootcamp.libraryproject.service.*;
import org.kodluyoruz.javabootcamp.libraryproject.util.controller.UtilKeyControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private MainService mainService;

    @RequestMapping(value = "/user",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity getUser(HttpServletRequest request){
        try {
            UtilKeyControl.keyControl(request,mainService);
            List<Library> libraryList = mainService.getUserLibrary(UUID.fromString(request.getHeader("x-user-key")));
            return new ResponseEntity<List<Library>>(libraryList,HttpStatus.OK);
        } catch (InvalidUserInfoException e) {
            e.printStackTrace();
            return new ResponseEntity(new Message(e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (InvalidAPIKeyException e) {
            e.printStackTrace();
            return new ResponseEntity(new Message(e.getMessage()),HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/search",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ResponseEntity getSearchLibrary(@RequestParam("query")String query,HttpServletRequest request){
        try {
            UtilKeyControl.keyControl(request,mainService);
            List<Library> librarys = mainService.searchLibrary(query,UUID.fromString(request.getHeader("x-user-key")));
            return new ResponseEntity(librarys,HttpStatus.OK);
        } catch (InvalidUserInfoException e) {
            e.printStackTrace();
            return new ResponseEntity(new Message(e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (InvalidAPIKeyException e) {
            e.printStackTrace();
            return new ResponseEntity(new Message(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/author",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity getAuthorList(HttpServletRequest request){
        try {
            UtilKeyControl.keyControl(request,mainService);
            List<Author> authors = mainService.getAllAuthor();
            return new ResponseEntity(authors,HttpStatus.OK);
        } catch (InvalidUserInfoException e) {
            e.printStackTrace();
            return new ResponseEntity(new Message(e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (InvalidAPIKeyException e) {
            e.printStackTrace();
            return new ResponseEntity(new Message(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/book",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity setBook(@RequestBody LibraryRequestContext libraryRequestContext,HttpServletRequest request){
        try {
            UtilKeyControl.keyControl(request,mainService);
            mainService.addBookToLibrary(libraryRequestContext,UUID.fromString(request.getHeader("x-user-key")));
            return new ResponseEntity(new Message("Kayıt Başarıyla Yapıldı"),HttpStatus.OK);
        } catch (InvalidUserInfoException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (InvalidAPIKeyException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/book",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ResponseEntity changeLibrary(@RequestParam("libraryId") String id,HttpServletRequest request){
        try {
            UtilKeyControl.keyControl(request,mainService);
            mainService.changeLibrary(UUID.fromString(id),UUID.fromString(request.getHeader("x-user-key")));
            return new ResponseEntity(new Message("Kayıt başarıyla güncellendi."),HttpStatus.OK);
        } catch (InvalidUserInfoException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (InvalidAPIKeyException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
