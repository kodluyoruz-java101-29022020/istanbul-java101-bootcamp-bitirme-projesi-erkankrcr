package junit.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.kodluyoruz.javabootcamp.libraryproject.application.ApplicationConfig;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Book;
import org.kodluyoruz.javabootcamp.libraryproject.dao.entity.Library;
import org.kodluyoruz.javabootcamp.libraryproject.service.BookManipulateService;
import org.kodluyoruz.javabootcamp.libraryproject.service.BookSelectService;
import org.kodluyoruz.javabootcamp.libraryproject.service.LibraryManipulateService;
import org.kodluyoruz.javabootcamp.libraryproject.service.LibrarySelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
@TestPropertySource({ "classpath:application.properties" })
public class BookIT {
    @Autowired
    private BookSelectService selectService;
    @Autowired
    private BookManipulateService manipulateService;
    @Autowired
    private LibrarySelectService librarySelectService;
    @Autowired
    private LibraryManipulateService libraryManipulateService;

    @Test
    @Order(1)
    @Transactional
    public void getBookList(){
        List<Book> books = selectService.allBook();
        Assert.assertTrue(books.size()>0);
    }

    @Test
    @Order(2)
    @Rollback
    @Transactional
    public void createBook(){
        Book book = new Book("BookTitle","BookDescription","BookComment",null, null);

        manipulateService.createBook(book);
        Assert.assertNotNull(selectService.findBookById(book.getId()));
    }

    @Test
    @Order(3)
    @Rollback
    @Transactional
    public void updateBook(){
        String testComment = "TestComment";
        List<Book> books = selectService.allBook();
        Book beforeBook = books.get(0);
        beforeBook.setComment(testComment);
        Assert.assertTrue(beforeBook.getComment().equals(testComment));
        manipulateService.updateBook(beforeBook);
        Book afterBook = selectService.findBookById(beforeBook.getId());
        Assert.assertNotNull(afterBook);
        Assert.assertTrue(afterBook.getComment().equals(testComment));
    }

    @Test
    @Order(4)
    @Rollback
    @Transactional
    public void deleteBook(){
        List<Book> books = selectService.allBook();
        Book book = books.get(0);
        Library library = librarySelectService.findLibraryById(UUID.fromString("1c7e4487-93c2-4f76-b27a-bca3e8828b43"));
        libraryManipulateService.deleteLibrary(library);
        manipulateService.deleteBook(book);
        Assert.assertNotNull(selectService.findBookById(book.getId()));
    }

}
