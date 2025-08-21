package com.ecommercial.shopping.adminservice.global.dto;

import com.ecommercial.shopping.adminservice.global.enums.AdminRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AdminPrincipal {
    private Long adminUserId;
    private String email;
    private AdminRoleEnum role;
    private Long companyId;
}
