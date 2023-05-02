package com.shoppingbasket.price.service.impl;

import com.shoppingbasket.price.model.BasketPriceResponse;
import com.shoppingbasket.price.model.Book;
import com.shoppingbasket.price.model.DiscountRules;
import com.shoppingbasket.price.repository.BookRepository;
import com.shoppingbasket.price.repository.DiscountRuleRepository;
import com.shoppingbasket.price.service.BasketPriceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class BasketPriceServiceImplTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DiscountRuleRepository discountRuleRepository;

    @Autowired
    private BasketPriceService basketPriceService;
    List<Book> bookList;
    List<DiscountRules> discountRules;
    @BeforeEach
    void setUp() {

        bookList = Stream.of(
                new Book(101, "Clean Code (Robert Martin, 2008)", 50),
                new Book(102, "The Clean Coder (Robert Martin, 2011)", 50),
                new Book(103, "Clean Architecture (Robert Martin, 2017)", 50),
                new Book(104, "Test Driven Development by Example (Kent Beck, 2003)", 50),
                new Book(105, "Working Effectively With Legacy Code (Michael C. Feathers, 2004)", 50)
        ).toList();

        discountRules = Stream.of(
                new DiscountRules(1, "One book no discount", 0),
                new DiscountRules(2, "Two different books", 5),
                new DiscountRules(3, "Three different books", 10),
                new DiscountRules(4, "Four different books", 20),
                new DiscountRules(5, "Five different books", 25)
        ).toList();




    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getShoppingCartPriceTest() {

        bookRepository.saveAll(bookList);
        discountRuleRepository.saveAll(discountRules);

        BasketPriceResponse expectedBasketResponse= new BasketPriceResponse(400,320,
                Arrays.asList(4,4));
        BasketPriceResponse basketPriceResponse = basketPriceService.getShoppingCartPrice(8);
        Assertions.assertEquals(expectedBasketResponse.toString(),basketPriceResponse.toString());

    }

    @Test
    void getShoppingCartPriceTestWithoutDiscountRulesInformation() {

        bookRepository.saveAll(bookList);

        BasketPriceResponse expectedBasketResponse= new BasketPriceResponse(400,0,
                new ArrayList<>());
        BasketPriceResponse basketPriceResponse = basketPriceService.getShoppingCartPrice(8);
        Assertions.assertEquals(expectedBasketResponse.toString(),basketPriceResponse.toString());

    }
}