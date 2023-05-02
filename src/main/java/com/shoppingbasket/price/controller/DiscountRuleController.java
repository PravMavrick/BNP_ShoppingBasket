package com.shoppingbasket.price.controller;

import com.shoppingbasket.price.model.DiscountRules;
import com.shoppingbasket.price.service.DiscountRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DiscountRuleController {

    @Autowired
    private DiscountRulesService discountRulesService;

    @PostMapping("/createDiscountRules")
    public ResponseEntity<List<DiscountRules>> createAllDiscountRules(@Validated @RequestBody List<DiscountRules> discountRulesList){
        List<DiscountRules> discRules = discountRulesService.saveAllDiscountRules(discountRulesList);
        return new ResponseEntity<>(discRules, HttpStatus.OK);
    }

}
