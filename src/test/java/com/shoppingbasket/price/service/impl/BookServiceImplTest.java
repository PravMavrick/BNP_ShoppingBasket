package com.shoppingbasket.price.service.impl;

import com.shoppingbasket.price.model.Book;
import com.shoppingbasket.price.repository.BookRepository;
import com.shoppingbasket.price.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Stream;


@SpringBootTest
class BookServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    Book book;
    List<Book> bookList;


    @BeforeEach
    void setUp() {
        bookList = Stream.of(
                new Book(101, "Clean Code (Robert Martin, 2008)", 50),
                new Book(102, "The Clean Coder (Robert Martin, 2011)", 50),
                new Book(103, "Clean Architecture (Robert Martin, 2017)", 50),
                new Book(104, "Test Driven Development by Example (Kent Beck, 2003)", 50),
                new Book(105, "Working Effectively With Legacy Code (Michael C. Feathers, 2004)", 50)
        ).toList();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveAllBooksTest() {

        Mockito.when(bookRepository.saveAll(Mockito.any())).thenReturn(bookList);
        List<Book> bookList1 = bookService.saveAllBooks(bookList);

        Assertions.assertNotNull(bookList1);
        Assertions.assertEquals(bookList.size(),bookList1.size());
    }


}