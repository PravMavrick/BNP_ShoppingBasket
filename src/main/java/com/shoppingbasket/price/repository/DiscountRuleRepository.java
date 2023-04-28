package com.shoppingbasket.price.repository;

import com.shoppingbasket.price.model.DiscountRules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRuleRepository extends JpaRepository<DiscountRules, Integer> {

}
