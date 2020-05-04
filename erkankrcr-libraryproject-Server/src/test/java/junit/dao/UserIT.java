package junit.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.kodluyoruz.javabootcamp.libraryproject.application.ApplicationConfig;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Book;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.kodluyoruz.javabootcamp.libraryproject.exception.InvalidUserInfoException;
import org.kodluyoruz.javabootcamp.libraryproject.service.UserManipulateService;
import org.kodluyoruz.javabootcamp.libraryproject.service.UserSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
@TestPropertySource({ "classpath:application.properties" })
public class UserIT {
    @Autowired
    private UserSelectService selectService;
    @Autowired
    private UserManipulateService manipulateService;

    @Test
    @Order(1)
    @Transactional
    public void getUser(){
        List<User> users = selectService.allUser();
        Assert.assertTrue(users.size()>0);
    }

    @Test
    @Order(2)
    @Rollback
    @Transactional
    public void createUser(){
        User tempUser = new User("tempUsername","tempPassword","Temp","Temp");
        manipulateService.createUser(tempUser);
        try {
            Assert.assertNotNull(selectService.findUserById(tempUser.getId()));
        } catch (InvalidUserInfoException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    @Order(3)
    @Rollback
    @Transactional
    public void updateUser(){
        String tempText = "TempText";
        User user = selectService.allUser().get(0);
        User tempUser = user;
        tempUser.setName(tempText);
        manipulateService.updateUser(tempUser);
        try {
            user = selectService.findUserById(tempUser.getId());
        } catch (InvalidUserInfoException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(user.getName().equals(tempText));
    }

    @Test
    @Order(4)
    @Rollback
    @Transactional
    public void deleteUser(){
        User user = selectService.allUser().get(0);
        manipulateService.deleteUser(user);
        try {
            Assert.assertNotNull(selectService.findUserById(user.getId()));
        } catch (InvalidUserInfoException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
