package com.shoppingbasket.price.exceptions;

public class TotalQuantityShouldNotBeLessThanORZero extends RuntimeException {
    public TotalQuantityShouldNotBeLessThanORZero(String s) {
        super(s);
    }
}
