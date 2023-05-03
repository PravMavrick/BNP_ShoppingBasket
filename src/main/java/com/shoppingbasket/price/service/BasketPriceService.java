package com.shoppingbasket.price.service;

import com.shoppingbasket.price.model.BasketPriceResponse;
import com.shoppingbasket.price.model.ShoppingBasket;

import java.util.List;

public interface BasketPriceService {

    BasketPriceResponse getShoppingCartPrice( List<ShoppingBasket> basketList);
}
