package com.shoppingbasket.price.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BasketPriceResponse {

    private double totalBasketPrice;
    private double discountedPrice;

}
