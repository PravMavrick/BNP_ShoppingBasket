package com.shoppingbasket.price.service.impl;

import com.shoppingbasket.price.model.Book;
import com.shoppingbasket.price.model.DiscountRules;
import com.shoppingbasket.price.repository.DiscountRuleRepository;
import com.shoppingbasket.price.service.DiscountRulesService;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiscountRulesServiceImplTest {

    @Autowired
    private DiscountRulesService discountRulesService;

    @MockBean
    private DiscountRuleRepository discountRuleRepository;

    List<DiscountRules> discountRulesList;

    @BeforeEach
    void setUp() {
        discountRulesList = Stream.of(
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
    void saveAllDiscountRulesTest() {
        Mockito.when(discountRuleRepository.saveAll(Mockito.any())).thenReturn(discountRulesList);
        List<DiscountRules> discountRulesList1 = discountRulesService.saveAllDiscountRules(discountRulesList);

        Assertions.assertNotNull(discountRulesList1);
        Assertions.assertEquals(discountRulesList.size(),discountRulesList1.size());
    }
}