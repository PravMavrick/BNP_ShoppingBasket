package com.shoppingbasket.price.service.impl;

import com.shoppingbasket.price.exceptions.TotalQuantityShouldNotBeLessThanORZero;
import com.shoppingbasket.price.model.BasketPriceResponse;
import com.shoppingbasket.price.model.Book;
import com.shoppingbasket.price.model.DiscountRules;
import com.shoppingbasket.price.model.ShoppingBasket;
import com.shoppingbasket.price.repository.BookRepository;
import com.shoppingbasket.price.repository.DiscountRuleRepository;
import com.shoppingbasket.price.service.BasketPriceService;
import com.shoppingbasket.price.service.utils.DiscountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class BasketPriceServiceImpl implements BasketPriceService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DiscountRuleRepository discountRuleRepository;

    public BasketPriceResponse getShoppingCartPrice(List<ShoppingBasket> basketList) {

        final double bookPrice = 50.0;

        Optional<ShoppingBasket> optionalShoppingBasket = basketList.stream().filter(e -> e.getBookQuantity() < 0).findAny();

        if(optionalShoppingBasket.isPresent())
            throw new IllegalArgumentException("Book quantity should not be negative");

        int totalQuantity = basketList.stream().map(ShoppingBasket::getBookQuantity).reduce(0, Integer::sum);

        if(totalQuantity==0)
            throw new TotalQuantityShouldNotBeLessThanORZero("Total quantity should not be zero.");

        Map<Integer, Integer> shoppingBasketMap = new ConcurrentHashMap<>();
        shoppingBasketMap = basketList.stream().collect(Collectors.toMap(ShoppingBasket::getBookId, ShoppingBasket::getBookQuantity));

        List<Book> bookList = bookRepository.findAll();

        if(bookList.size()==0){
            //throw new BooksAreNotRegistered("Books are not registered into application.");
            throw new NullPointerException("Books are not registered into application.");
        }

        List<DiscountRules> discountRules = discountRuleRepository.findAll();
        Map<Integer, Double> discPriceMap = new HashMap<>();

        if (discountRules.size() != 0) {
            Map<Integer, Integer> discRuleMap = discountRules.stream().collect(Collectors.toMap(DiscountRules::getDiscRuleId, DiscountRules::getDiscPercentage));

            discRuleMap.forEach((ruleId, discPercentage) -> {
                double discountValue = (ruleId * bookPrice) - ((ruleId * bookPrice) * (discPercentage / 100.0));
                discPriceMap.put(ruleId, discountValue);
            });

            return new BasketPriceResponse(totalQuantity * bookPrice, DiscountUtils.getMinPriceOfBasketWithGivenQuantity(shoppingBasketMap, discPriceMap));

        }else {

           return new BasketPriceResponse(totalQuantity * bookPrice, 0);

        }

    }
}
