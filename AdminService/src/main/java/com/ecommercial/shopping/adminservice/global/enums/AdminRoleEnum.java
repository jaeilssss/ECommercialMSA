package com.ecommercial.shopping.adminservice.global.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum AdminRoleEnum {

    STAFF("스태프"),
    MANAGER("중간 관리자"),
    SUPER_ADMIN("최고 관리자");

    private String role;

}
