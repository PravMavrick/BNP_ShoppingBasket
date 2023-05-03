package com.shoppingbasket.price.exceptions;


import com.shoppingbasket.price.model.ShoppingBasket;
import com.shoppingbasket.price.service.impl.BasketPriceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AssertThrows {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void assertThrowsTotalQuantityShouldNotBeLessThanOrZeroTest() {
        BasketPriceServiceImpl shoppingBasketServiceImpl =new BasketPriceServiceImpl();

        List<ShoppingBasket> shoppingBasketList = Stream.of(
                new ShoppingBasket(1, "Clean Code (Robert Martin, 2008)", 0)
        ).toList();

        TotalQuantityShouldNotBeLessThanORZero totalQuantityShouldNotBeLessThanORZero= Assertions.assertThrows(
                TotalQuantityShouldNotBeLessThanORZero.class, ()-> {
            shoppingBasketServiceImpl.getShoppingCartPrice(shoppingBasketList);
                }, "Total quantity should not be either zero or in negative.");

        Assertions.assertEquals("Total quantity should not be zero.",totalQuantityShouldNotBeLessThanORZero.getMessage());
    }

    @Test
    public void assertThrowsBooksAreNotRegisteredIntoApplicationTest() {

        BasketPriceServiceImpl basketPriceService = new BasketPriceServiceImpl();

        List<ShoppingBasket> shoppingBasketList = Stream.of(
                new ShoppingBasket(1, "Clean Code (Robert Martin, 2008)", 2)
        ).toList();

        assertThatThrownBy(() -> basketPriceService.getShoppingCartPrice(shoppingBasketList))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("this.bookRepository" );

    }

    @Test
    public void assertThrowsBookQuantityShouldNotBeNegativeTest() {

        BasketPriceServiceImpl basketPriceService = new BasketPriceServiceImpl();

        List<ShoppingBasket> shoppingBasketList = Stream.of(
                new ShoppingBasket(1, "Clean Code (Robert Martin, 2008)", -1)
        ).toList();

        assertThatThrownBy(() -> basketPriceService.getShoppingCartPrice(shoppingBasketList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Book quantity should not be negative" );

    }
}
