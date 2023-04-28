package com.shoppingbasket.price.service;

import com.shoppingbasket.price.model.Book;

import java.util.List;

public interface BookService {
    List<Book> saveAllBooks(List<Book> bookList);
}
