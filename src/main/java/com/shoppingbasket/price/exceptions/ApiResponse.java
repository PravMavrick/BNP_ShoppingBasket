package com.shoppingbasket.price.exceptions;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponse {

    private String message;
    private boolean status;

}
