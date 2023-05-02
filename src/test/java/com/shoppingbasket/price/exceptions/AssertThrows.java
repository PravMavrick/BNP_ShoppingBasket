package com.shoppingbasket.price.exceptions;


import com.shoppingbasket.price.controller.BasketPriceController;
import com.shoppingbasket.price.service.impl.BasketPriceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AssertThrows {



    @BeforeEach
    void setUp() {
    }

    @Test
    public void assertThrowsTotalQuantityShouldNotBeLessThanOrZero() {
        BasketPriceServiceImpl shoppingBasketServiceImpl =new BasketPriceServiceImpl();

        TotalQuantityShouldNotBeLessThanORZero totalQuantityShouldNotBeLessThanORZero= Assertions.assertThrows(
                TotalQuantityShouldNotBeLessThanORZero.class, ()-> {
            shoppingBasketServiceImpl.getShoppingCartPrice(0);
                }, "Total quantity should not be either zero or in negative.");

        Assertions.assertEquals("Total quantity should not be either zero or in negative.",totalQuantityShouldNotBeLessThanORZero.getMessage());
    }

    @Test
    public void assertThrowsBooksAreNotRegisteredIntoApplication() {

        BasketPriceServiceImpl basketPriceService = new BasketPriceServiceImpl();

        assertThatThrownBy(() -> basketPriceService.getShoppingCartPrice(1))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("this.bookRepository" );

    }
}
