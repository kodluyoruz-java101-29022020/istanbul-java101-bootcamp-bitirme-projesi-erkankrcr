package org.kodluyoruz.javabootcamp.libraryproject.controller;

import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.kodluyoruz.javabootcamp.libraryproject.dao.model.Message;
import org.kodluyoruz.javabootcamp.libraryproject.dao.model.UserRequestContext;
import org.kodluyoruz.javabootcamp.libraryproject.exception.InvalidUserInfoException;
import org.kodluyoruz.javabootcamp.libraryproject.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private MainService mainService;

    @RequestMapping(value = "/login",method = RequestMethod.GET , produces = "application/json;charset=UTF-8")
    public ResponseEntity<User> loginUser(@RequestParam String username ,@RequestParam String password){
        try {
            if(username.isEmpty() || password.isEmpty()){
                return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
            }
            User user = mainService.getUserByUserNameAndPassword(username,password);
            return new ResponseEntity<User>(user,HttpStatus.OK);
        } catch (InvalidUserInfoException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/sign",method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
    public ResponseEntity<User> signUser(@RequestBody UserRequestContext userRequestContext){
        try {
            if(mainService.chechUser(userRequestContext)){
                return new ResponseEntity(new Message("Kullanıcı Bulunmaktadır."),HttpStatus.BAD_REQUEST);
            }else{
                User resultUser = mainService.createUserByUserContext(userRequestContext);
                return new ResponseEntity(resultUser,HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new Message("Hata ile Karşılaşıldı : "+e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
