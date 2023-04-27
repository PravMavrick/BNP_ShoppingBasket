package com.shoppingbasket.price.controller;

import com.shoppingbasket.price.model.Book;
import com.shoppingbasket.price.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @PostMapping("/createAllBooks")
    public ResponseEntity<List<Book>> createAllBooks(@Validated @RequestBody List<Book> bookList){
        List<Book> books = bookServiceImpl.saveAllBooks(bookList);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
