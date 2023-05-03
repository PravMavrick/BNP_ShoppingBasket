package com.shoppingbasket.price.service.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DiscountUtils {

    private static final Logger logger = LoggerFactory.getLogger(DiscountUtils.class);

    public static double getMinPriceOfBasketWithGivenQuantity(
                Map<Integer, Integer> shoppingCartQuantity,
                Map<Integer, Double> discPriceMap) {

        int maxGroupSize = 5;
        double totalMinDiscPrice = 0.0;

        List<Integer> tempList = new ArrayList<>();
        Map<Integer, Integer> tempShoppingCartQuantity = new ConcurrentHashMap<>();

        while (maxGroupSize > 0) {

            tempShoppingCartQuantity.putAll(shoppingCartQuantity);
            logger.info("-------------------------------------------------------------------------------------");

            int sum = 1;
            double totalDiscPrice = 0.0;

            while (sum > 0) {
                tempList.clear();
                int tempMaxGroupSize = maxGroupSize;

                tempShoppingCartQuantity.forEach((key, value) -> {
                    if (value > 0) {
                        if (tempList.size() < tempMaxGroupSize) {
                            tempList.add(key);
                            tempShoppingCartQuantity.put(key, tempShoppingCartQuantity.get(key) - 1);
                        }
                    }
                });

                logger.info(tempList.toString());
                totalDiscPrice = totalDiscPrice + discPriceMap.get(tempList.size());
                sum = tempShoppingCartQuantity.values().stream().mapToInt(Integer::intValue).sum();
            }
            logger.info("-------------------------------------------------------------------------------------");

            if (totalMinDiscPrice == 0) {
                totalMinDiscPrice = totalDiscPrice;
            } else if (totalMinDiscPrice > totalDiscPrice) {
                totalMinDiscPrice = totalDiscPrice;
            }
            logger.info("Total price of current combination : " +totalDiscPrice +"   Minimum Price till now : " + totalMinDiscPrice);

            tempShoppingCartQuantity.clear();
            maxGroupSize -= 1;
        }

        logger.info("-------------------------------------------------------------------------------------");

        logger.info("Final Minimum basket price : " + totalMinDiscPrice);
        return totalMinDiscPrice;
    }

}
