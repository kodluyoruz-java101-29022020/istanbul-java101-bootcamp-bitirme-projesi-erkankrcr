package junit.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.kodluyoruz.javabootcamp.libraryproject.application.ApplicationConfig;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Author;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.kodluyoruz.javabootcamp.libraryproject.service.AuthorManipulateService;
import org.kodluyoruz.javabootcamp.libraryproject.service.AuthorSelectService;
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
public class AuthorIT {
    @Autowired
    private AuthorSelectService selectService;
    @Autowired
    private AuthorManipulateService manipulateService;


    @Test
    @Order(1)
    @Transactional
    public void getAuthor(){
        Assert.assertTrue(selectService.allAuthor().size()>0);
    }

    @Test
    @Order(2)
    @Rollback
    @Transactional
    public void createAuthor(){
        String tempAuthorName ="TempAuthor";
        Author author = new Author(tempAuthorName,tempAuthorName);
        manipulateService.createAuthor(author);
        Assert.assertNotNull(selectService.findAuthorById(author.getId()));
    }

    @Test
    @Order(3)
    @Rollback
    @Transactional
    public void updateAuthor(){
        String tempAuthorName ="TempAuthor";
        Author author = new Author(tempAuthorName,tempAuthorName);
        manipulateService.createAuthor(author);
        Assert.assertNotNull(selectService.findAuthorById(author.getId()));
    }


    @Test
    @Order(4)
    @Rollback
    @Transactional
    public void deleteAuthor(){
        Author author = selectService.allAuthor().get(0);
        manipulateService.deleteAuthor(author);
        Assert.assertNotNull(selectService.findAuthorById(author.getId()));
    }
}
