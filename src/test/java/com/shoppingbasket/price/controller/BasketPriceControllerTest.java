package com.shoppingbasket.price.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingbasket.price.model.BasketPriceResponse;
import com.shoppingbasket.price.model.Book;
import com.shoppingbasket.price.model.DiscountRules;
import com.shoppingbasket.price.model.ShoppingBasket;
import com.shoppingbasket.price.repository.BookRepository;
import com.shoppingbasket.price.repository.DiscountRuleRepository;
import com.shoppingbasket.price.service.BasketPriceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BasketPriceControllerTest {

    @MockBean
    private BasketPriceService basketPriceService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private DiscountRuleRepository discountRuleRepository;

    @Autowired
    private BookRepository bookRepository;

    private List<ShoppingBasket> shoppingBasketList;

    private List<DiscountRules> discountRules;

    private List<Book> bookList;

    @BeforeEach
    void setUp() {

        bookList = Stream.of(
                new Book(101, "Clean Code (Robert Martin, 2008)", 50),
                new Book(102, "The Clean Coder (Robert Martin, 2011)", 50),
                new Book(103, "Clean Architecture (Robert Martin, 2017)", 50),
                new Book(104, "Test Driven Development by Example (Kent Beck, 2003)", 50),
                new Book(105, "Working Effectively With Legacy Code (Michael C. Feathers, 2004)", 50)
        ).toList();

        shoppingBasketList = Stream.of(
                new ShoppingBasket(1, "Clean Code (Robert Martin, 2008)", 2),
                new ShoppingBasket(2, "The Clean Coder (Robert Martin, 2011)", 2),
                new ShoppingBasket(3, "Clean Architecture (Robert Martin, 2017)", 2),
                new ShoppingBasket(4, "Test Driven Development by Example (Kent Beck, 2003)", 1),
                new ShoppingBasket(5, "Working Effectively With Legacy Code (Michael C. Feathers, 2004)", 1)
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
    void calculateShoppingCartPriceWithDiscountRulesTest() throws Exception {
        BasketPriceResponse basketPriceResponse=new BasketPriceResponse(400.0,320.5);
        discountRuleRepository.saveAll(discountRules);
        bookRepository.saveAll(bookList);

        Mockito.when(basketPriceService.getShoppingCartPrice(Mockito.any())).thenReturn(basketPriceResponse);
        this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/getPrice")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(Objects.requireNonNull(convertObjectToJsonString(shoppingBasketList)))
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalBasketPrice").exists());
    }

    @Test
    void calculateShoppingCartPriceWithoutDiscountRulesTest() throws Exception {
        BasketPriceResponse basketPriceResponse=new BasketPriceResponse(400.0,0.0);
        bookRepository.saveAll(bookList);

        Mockito.when(basketPriceService.getShoppingCartPrice(Mockito.any())).thenReturn(basketPriceResponse);
        this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/getPrice")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(Objects.requireNonNull(convertObjectToJsonString(shoppingBasketList)))
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalBasketPrice").exists());
    }

    private String convertObjectToJsonString(List<ShoppingBasket> basketList) {
        try {
            return new ObjectMapper().writeValueAsString(basketList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}