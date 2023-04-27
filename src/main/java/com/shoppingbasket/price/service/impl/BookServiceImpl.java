package com.shoppingbasket.price.service.impl;

import com.shoppingbasket.price.model.Book;
import com.shoppingbasket.price.repository.BookRepository;
import com.shoppingbasket.price.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> saveAllBooks(List<Book> bookList) {
        return bookRepository.saveAll(bookList);
    }
}
