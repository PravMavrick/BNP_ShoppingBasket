package com.shoppingbasket.price.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingbasket.price.model.DiscountRules;
import com.shoppingbasket.price.service.DiscountRulesService;
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
class DiscountRuleControllerTest {

    @MockBean
    private DiscountRulesService discountRulesService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ModelMapper mapper;

    List<DiscountRules> discountRules;

    @BeforeEach
    void setUp() {
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
    void createAllDiscountRules() throws Exception {
        Mockito.when(discountRulesService.saveAllDiscountRules(Mockito.any())).thenReturn(discountRules);
        this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/createDiscountRules")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(Objects.requireNonNull(convertObjectToJsonString(discountRules)))
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..[0].discDescription").exists());

    }

    private String  convertObjectToJsonString(List<DiscountRules> discountRules) {
        try {
            return new ObjectMapper().writeValueAsString(discountRules);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}