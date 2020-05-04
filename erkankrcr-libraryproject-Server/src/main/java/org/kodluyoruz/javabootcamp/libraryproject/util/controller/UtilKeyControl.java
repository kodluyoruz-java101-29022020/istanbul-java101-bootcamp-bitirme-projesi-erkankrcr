package org.kodluyoruz.javabootcamp.libraryproject.util.controller;

import org.kodluyoruz.javabootcamp.libraryproject.exception.InvalidAPIKeyException;
import org.kodluyoruz.javabootcamp.libraryproject.exception.InvalidUserInfoException;
import org.kodluyoruz.javabootcamp.libraryproject.service.MainService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class UtilKeyControl {
    public static void keyControl(HttpServletRequest request, MainService mainService) throws InvalidAPIKeyException, InvalidUserInfoException {
        String userId = request.getHeader("x-user-key");
        if (userId == null){
            throw  new InvalidAPIKeyException("Kullanıcı ID bilgisi girilmelidir. (Header key = x-user-key)");
        }
        UUID uuid = UUID.fromString(userId);
        if (!mainService.chechUserById(uuid)){
            throw  new InvalidAPIKeyException("Kullanıcı ID bilgisi yanlış yada hatalı girildi");
        }

    }
}
