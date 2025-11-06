package com.ecommercial.shopping.productservice.global.dto;

import com.ecommercial.shopping.productservice.global.enums.AdminRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AdminPrincipal {
    private Long adminUserId;
    private String email;
    private AdminRole role;
    private Long companyId;
}
