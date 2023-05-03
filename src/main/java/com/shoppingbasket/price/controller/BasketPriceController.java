package com.shoppingbasket.price.controller;

import com.shoppingbasket.price.exceptions.TotalQuantityShouldNotBeLessThanORZero;
import com.shoppingbasket.price.model.BasketPriceResponse;
import com.shoppingbasket.price.model.ShoppingBasket;
import com.shoppingbasket.price.service.BasketPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BasketPriceController {

    @Autowired
    private BasketPriceService basketPriceService;

    @PostMapping("/getPrice")
    public ResponseEntity<BasketPriceResponse> calculateShoppingCartPrice(@RequestBody List<ShoppingBasket> basketList){

        return new ResponseEntity<>(basketPriceService.getShoppingCartPrice(basketList), HttpStatus.OK);
    }

}
