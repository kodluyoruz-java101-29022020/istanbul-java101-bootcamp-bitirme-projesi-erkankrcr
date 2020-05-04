package junit.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.kodluyoruz.javabootcamp.libraryproject.application.ApplicationConfig;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Book;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Library;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.User;
import org.kodluyoruz.javabootcamp.libraryproject.service.BookSelectService;
import org.kodluyoruz.javabootcamp.libraryproject.service.LibraryManipulateService;
import org.kodluyoruz.javabootcamp.libraryproject.service.LibrarySelectService;
import org.kodluyoruz.javabootcamp.libraryproject.service.UserSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
@TestPropertySource({ "classpath:application.properties" })
public class LibraryIT {
    @Autowired
    private LibrarySelectService selectService;
    @Autowired
    private LibraryManipulateService manipulateService;
    @Autowired
    private UserSelectService userSelectService;
    @Autowired
    private BookSelectService bookSelectService;

    @Test
    @Order(1)
    @Transactional
    public void getLibrary(){
        Assert.assertTrue(selectService.allLibrary().size()>0);
    }

    @Test
    @Order(2)
    @Rollback
    @Transactional
    public void createLibrary(){
        Library library = selectService.allLibrary().get(0);
        Library tempLibrary = new Library(
                library.getReadingStart(),
                library.getReadingEnd(),
                library.getAddedTime(),
                false,userSelectService.allUser().get(0),
                bookSelectService.allBook().get(0));
        manipulateService.createLibrary(tempLibrary);
        Assert.assertNotNull(selectService.findLibraryById(tempLibrary.getId()));
    }

    @Test
    @Order(3)
    @Rollback
    @Transactional
    public void updateLibrary(){
        Library library = selectService.allLibrary().get(0);
        Library tempLibrary = library;
        boolean flag = tempLibrary.getComplete();
        if (library.getComplete()){
            tempLibrary.setComplete(false);
        }else{
            tempLibrary.setComplete(true);
        }
        manipulateService.updateLibrary(tempLibrary);
        tempLibrary = selectService.findLibraryById(tempLibrary.getId());
        Assert.assertTrue(tempLibrary.getComplete() != flag);
    }

    @Test
    @Order(4)
    @Rollback
    @Transactional
    public void deleteLibrary(){
        Library library = selectService.allLibrary().get(0);
        manipulateService.deleteLibrary(library);
        Assert.assertNotNull(selectService.findLibraryById(library.getId()));
    }
}
