package mock.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Author;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Book;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Library;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.kodluyoruz.javabootcamp.libraryproject.exception.InvalidUserInfoException;
import org.kodluyoruz.javabootcamp.libraryproject.service.MainService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class MainServiceMockTest {

    @Mock
    private MainService mainService;
    User user,user2;
    Book tempBook;
    Library tempLibrary;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        user = new User("tempUser","tempPassword","tempname","templastName");
        user2 = new User("tempUser2","tempPassword2","tempname2","templastName2");
        tempBook = new Book(
                "TempBook",
                "TempDescription",
                "TempComment",
                new Author("TempName","TempLastName"),
                null);
        tempLibrary = new Library(null,null,null,true,user,tempBook);
    }

    @Test
    public void getUser(){
        try {
            Mockito.when(mainService.getUser(user.getId())).thenReturn(user);
            Mockito.when(mainService.getUser(user2.getId())).thenReturn(user2);
            Assert.assertNotNull(mainService.getUser(user.getId()));
            Assert.assertNotNull(mainService.getUser(user2.getId()));
        } catch (InvalidUserInfoException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getLibrary(){
        List<Library> libraryList = new ArrayList<>();
        libraryList.add(tempLibrary);
        Mockito.when(mainService.getLibrary(tempLibrary.getId())).thenReturn(tempLibrary);
        Assert.assertNotNull(mainService.getLibrary(tempLibrary.getId()));
    }

    @Test
    public void getAuthor(){
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("TempName","TempLastName"));
        Mockito.when(mainService.getAllAuthor()).thenReturn(authors);
        Assert.assertTrue(mainService.getAllAuthor().size()>0);
    }

    @Test
    public void getQueryLibrary(){
        List<Library> libraryList = new ArrayList<>();
        libraryList.add(tempLibrary);
        Mockito.when(mainService.searchLibrary("Temp",user.getId())).thenReturn(libraryList);
        List<Library> libraryList1 = mainService.searchLibrary("Temp",user.getId());
        Assert.assertTrue(libraryList.size()>0);
        Assert.assertTrue(libraryList1.get(0).getBooks().getTitle().contains("Temp"));
    }
}
