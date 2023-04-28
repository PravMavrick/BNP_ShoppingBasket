package com.shoppingbasket.price.service.impl;

import com.shoppingbasket.price.model.DiscountRules;
import com.shoppingbasket.price.repository.BookRepository;
import com.shoppingbasket.price.repository.DiscountRuleRepository;
import com.shoppingbasket.price.service.DiscountRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountRulesServiceImpl implements DiscountRulesService {

    @Autowired
    private DiscountRuleRepository discountRuleRepository;


    @Override
    public List<DiscountRules> saveAllDiscountRules(List<DiscountRules> discountRulesList) {

        return discountRuleRepository.saveAll(discountRulesList);
    }
}
