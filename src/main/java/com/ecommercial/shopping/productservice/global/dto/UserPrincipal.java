package com.ecommercial.shopping.productservice.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserPrincipal {
    private String email;
    private Long userId;

}
