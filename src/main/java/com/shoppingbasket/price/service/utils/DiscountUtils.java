package com.shoppingbasket.price.service.utils;

import com.shoppingbasket.price.model.BasketPriceResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiscountUtils {

    static int[] dp = new int[200];
    //static int count = 0;
    static ArrayList<ArrayList<Integer>> finalList = new ArrayList<ArrayList<Integer>>();

    static void collectSample(int idx) {
        int oneCount = 0;
        ArrayList<Integer> tempList = new ArrayList<>();

        for (int i = 1; i < idx; i++) {
            if (dp[i] == 1) {
                oneCount++;
            }
        }
        if (oneCount > 1)
            return;

        for (int i = 1; i < idx; i++) {
            tempList.add(dp[i]);
        }
        finalList.add(tempList);
    }

    static void solve(int remSum, int maxVal, int idx, int count) {
        if (remSum == 0) {
            collectSample(idx);
            count++;
            return;
        }

        for (int i = maxVal; i >= 1; i--) {
            if (i <= remSum) {
                dp[idx] = i;
                solve(remSum - i, i, idx + 1, count);
            }
        }
    }

    // Driver code
    public static ArrayList<ArrayList<Integer>> getNumberOfSamples(int totalQuantity) {
        int count = 0;
        finalList.clear();
        solve(totalQuantity, 5, 1, count);
        return finalList;
    }

    public static BasketPriceResponse getMinPriceOfBasketWithGivenSamplesSets(
                            ArrayList<ArrayList<Integer>> numberOfSamples,
                            Map<Integer, Double> discPriceMap) {

        double totalPrice = 0.0, totalMinPrice = 0.0;
        List<Integer> selectedSample = new ArrayList<>();

        for (ArrayList<Integer> integerArrayList : numberOfSamples) {
            System.out.println(integerArrayList);
            totalPrice = 0.0;
            for (Integer num : integerArrayList) {
                Double aDouble = discPriceMap.get(num);
                totalPrice = totalPrice + aDouble;
            }

            System.out.println("Min price : " + totalMinPrice + " total price : " + totalPrice);

            if (totalMinPrice == 0) {
                totalMinPrice = totalPrice;
                selectedSample = integerArrayList;
            } else if (totalMinPrice > totalPrice) {
                totalMinPrice = totalPrice;
                selectedSample = integerArrayList;
            }
        }

        Double totalFinalPrice = selectedSample.stream().map(e -> e * 50.0).reduce(0.0, Double::sum);

        BasketPriceResponse basketPriceResponse = new BasketPriceResponse(totalFinalPrice, totalMinPrice, selectedSample);
        System.out.println("Final price is --->  " + totalFinalPrice + " min price --> " + totalMinPrice + " Final Set : " + selectedSample);

        return basketPriceResponse;

    }


}
