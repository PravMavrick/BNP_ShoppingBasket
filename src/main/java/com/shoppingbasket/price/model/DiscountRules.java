package com.shoppingbasket.price.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "discountRule_tbl")
public class DiscountRules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int discRuleId;

    @NotBlank
    private String discDescription;

    private int discPercentage;
}
