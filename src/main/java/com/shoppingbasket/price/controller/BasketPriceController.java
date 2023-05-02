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

@RestController
@RequestMapping("/api")
public class BasketPriceController {

    @Autowired
    private BasketPriceService basketPriceService;

    @PostMapping("/getPrice")
    public ResponseEntity<BasketPriceResponse> calculateShoppingCartPrice(@RequestBody List<ShoppingBasket> basketList){

        int totalQuantity = basketList.stream().map(ShoppingBasket::getBookQuantity).reduce(0, Integer::sum);


        return new ResponseEntity<>(basketPriceService.getShoppingCartPrice(totalQuantity), HttpStatus.OK);
    }

}
