package junit.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.kodluyoruz.javabootcamp.libraryproject.application.ApplicationConfig;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.kodluyoruz.javabootcamp.libraryproject.dao.model.UserRequestContext;
import org.kodluyoruz.javabootcamp.libraryproject.exception.InvalidUserInfoException;
import org.kodluyoruz.javabootcamp.libraryproject.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = ApplicationConfig.class)
@TestPropertySource({ "classpath:application.properties" })
public class UserControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int tomcatPortNo;

    @Test
    @Order(1)
    public void testRestTemplate() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("https://www.google.com", String.class);

        System.out.println(tomcatPortNo);

        Assert.assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
        Assert.assertTrue(response.getBody().length() > 0);
    }

    @Test
    @Order(2)
    public void testLogin(){
        String url ="http://127.0.0.1:"+tomcatPortNo+"/user/login?username=ErkanKrcr&password=Erkan1Krcr";
        ResponseEntity<User> responseEntity =
                testRestTemplate.getForEntity(url,User.class);
        User user = responseEntity.getBody();

        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
        Assert.assertNotNull(user);
        Assert.assertFalse("".equals(user.getId().toString()));
    }

    @Test
    @Order(3)
    public void testSign(){
        String url ="http://127.0.0.1:"+tomcatPortNo+"/user/sign";
        UserRequestContext userRequestContext = new UserRequestContext("tempUser","tempUser","tempUser","temp123");
        ResponseEntity<User> responseEntity = testRestTemplate.postForEntity(url,userRequestContext,User.class);
        User user = responseEntity.getBody();

        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
        Assert.assertNotNull(user);
        Assert.assertFalse("".equals(user.getId().toString()));
    }
}
