package com.shoppingbasket.price.service;

import com.shoppingbasket.price.model.DiscountRules;

import java.util.List;

public interface DiscountRulesService {
    List<DiscountRules> saveAllDiscountRules(List<DiscountRules> discountRulesList);
}
