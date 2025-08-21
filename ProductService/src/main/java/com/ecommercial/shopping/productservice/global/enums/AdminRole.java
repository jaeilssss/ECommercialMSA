package com.ecommercial.shopping.productservice.global.enums;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AdminRole {
    STAFF("스태프"),
    MANAGER("중간 관리자"),
    SUPER_ADMIN("최고 관리자");

    private String role;

}

