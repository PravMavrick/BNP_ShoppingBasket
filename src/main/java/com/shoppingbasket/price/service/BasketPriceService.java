package com.shoppingbasket.price.service;

import com.shoppingbasket.price.model.BasketPriceResponse;

public interface BasketPriceService {

    BasketPriceResponse getShoppingCartPrice(int totalQuantity);
}
