package junit.controller;


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.kodluyoruz.javabootcamp.libraryproject.application.ApplicationConfig;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Author;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Library;
import org.kodluyoruz.javabootcamp.libraryproject.dao.model.LibraryRequestContext;
import org.kodluyoruz.javabootcamp.libraryproject.dao.model.Message;
import org.kodluyoruz.javabootcamp.libraryproject.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = ApplicationConfig.class)
@TestPropertySource({ "classpath:application.properties" })
public class LibraryControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int tomcatPortNo;
    @Autowired
    private MainService mainService;
    String userKey = "894e1624-2d42-4746-8d3f-c5d3f4eead3c";
    String query = "e";
    String url = "http://localhost:"+tomcatPortNo+"/library/";
    String libraryId = "1c7e4487-93c2-4f76-b27a-bca3e8828b43";
    String title = "JunitTest";

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
    public void getUserLibrary(){
        String userKey = "894e1624-2d42-4746-8d3f-c5d3f4eead3c";
        String url = "http://localhost:"+tomcatPortNo+"/library/";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("x-user-key", userKey);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(httpHeaders);

        ResponseEntity<List<Library>> response = testRestTemplate.exchange(
                url+"user",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<Library>>() {} );
        List<Library> libraryList = response.getBody();
        Assert.assertFalse(libraryList.isEmpty());
        Assert.assertNotNull(libraryList.get(0));
    }
    @Test
    @Order(3)
    public void getAuthor(){
        String userKey = "894e1624-2d42-4746-8d3f-c5d3f4eead3c";
        String url = "http://localhost:"+tomcatPortNo+"/library/";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("x-user-key", userKey);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(httpHeaders);

        ResponseEntity<List<Author>> response = testRestTemplate.exchange(
                url+"author",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<Author>>() {} );
        List<Author> authors = response.getBody();
        Assert.assertFalse(authors.isEmpty());
        Assert.assertNotNull(authors.get(0));
    }

    @Test
    @Order(4)
    public void getSearch(){
        String userKey = "894e1624-2d42-4746-8d3f-c5d3f4eead3c";
        String query = "e";
        String url = "http://localhost:"+tomcatPortNo+"/library/";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("x-user-key", userKey);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(httpHeaders);

        ResponseEntity<List<Library>> response = testRestTemplate.exchange(
                url+"search?query="+query,
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<Library>>() {} );
        List<Library> libraryList = response.getBody();
        Assert.assertFalse(libraryList.isEmpty());
        Assert.assertNotNull(libraryList.get(0));
        Assert.assertTrue(libraryList.get(0).getBooks().getTitle().contains(query));
    }

    @Test
    @Order(5)
    public void setChangeLibrary(){
        String userKey = "894e1624-2d42-4746-8d3f-c5d3f4eead3c";
        String url = "http://localhost:"+tomcatPortNo+"/library/";
        String libraryId = "1c7e4487-93c2-4f76-b27a-bca3e8828b43";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("x-user-key", userKey);
        Library beforelibrary = mainService.getLibrary(UUID.fromString(libraryId));

        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<Message> response = testRestTemplate.exchange(
                url+"book?libraryId="+libraryId,
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<Message>() {} );
        Library afterlibrary = mainService.getLibrary(UUID.fromString(libraryId));
        Assert.assertFalse(beforelibrary.getComplete() == afterlibrary.getComplete());

    }

    @Test
    @Order(6)
    public void addLibrary(){
        String userKey = "894e1624-2d42-4746-8d3f-c5d3f4eead3c";
        String url = "http://localhost:"+tomcatPortNo+"/library/";
        String title = "JunitTest";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("x-user-key", userKey);
        LibraryRequestContext libraryRequestContext =
                new LibraryRequestContext(
                        title,
                        "Deneme",
                        "kötü",
                        "66664c45-3ffd-4e53-9ceb-eeeb44748a49",
                        "2020-12-12");

        HttpEntity<LibraryRequestContext> httpEntity =
                new HttpEntity<LibraryRequestContext>(libraryRequestContext,httpHeaders);

        ResponseEntity<Message> response = testRestTemplate.exchange(
                url+"book",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<Message>() {} );
        List<Library> librarys = mainService.searchLibrary(title,UUID.fromString(userKey));
        Assert.assertTrue(librarys.get(0).getBooks().getTitle().contains(title));
    }
}
