package com.shoppingbasket.price.service.impl;

import com.shoppingbasket.price.exceptions.TotalQuantityShouldNotBeLessThanORZero;
import com.shoppingbasket.price.model.BasketPriceResponse;
import com.shoppingbasket.price.model.Book;
import com.shoppingbasket.price.model.DiscountRules;
import com.shoppingbasket.price.repository.BookRepository;
import com.shoppingbasket.price.repository.DiscountRuleRepository;
import com.shoppingbasket.price.service.BasketPriceService;
import com.shoppingbasket.price.service.utils.DiscountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BasketPriceServiceImpl implements BasketPriceService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DiscountRuleRepository discountRuleRepository;

    public BasketPriceResponse getShoppingCartPrice(int totalQuantity) {

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
                double discountValue = (ruleId * 50) - ((ruleId * 50) * (discPercentage / 100.0));
                discPriceMap.put(ruleId, discountValue);
            });

            ArrayList<ArrayList<Integer>> numberOfSamples = DiscountUtils.getNumberOfSamples(totalQuantity);

            return DiscountUtils.getMinPriceOfBasketWithGivenSamplesSets(numberOfSamples, discPriceMap);
        }else {
           return new BasketPriceResponse(totalQuantity*50,0,new ArrayList<>());
        }

    }
}
