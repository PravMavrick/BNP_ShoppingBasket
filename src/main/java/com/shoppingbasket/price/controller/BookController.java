package com.shoppingbasket.price.controller;

import com.shoppingbasket.price.model.Book;
import com.shoppingbasket.price.service.BookService;
import com.shoppingbasket.price.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/createAllBooks")
    public ResponseEntity<List<Book>> createAllBooks(@Valid @RequestBody List<Book> bookList){
        List<Book> books = bookService.saveAllBooks(bookList);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
