package com.shoppingbasket.price.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingbasket.price.model.Book;
import com.shoppingbasket.price.service.BookService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ModelMapper mapper;

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
    void createAllBooksTest() throws Exception {

        Mockito.when(bookService.saveAllBooks(Mockito.any())).thenReturn(bookList);
        this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/createAllBooks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(Objects.requireNonNull(convertObjectToJsonString(bookList)))
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..[0].bookId").exists());
    }

    private String convertObjectToJsonString(List<Book> books) {
        try {
            return new ObjectMapper().writeValueAsString(bookList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}