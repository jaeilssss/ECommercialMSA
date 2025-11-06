package com.ecommercial.shopping.productservice.global.enums;


import com.ecommercial.shopping.productservice.global.error.JwtTokenError;
import com.ecommercial.shopping.productservice.global.exception.MyException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AdminRole {
    STAFF("스태프", "ROLE_STAFF"),
    MANAGER("중간 관리자", "ROLE_MANAGER"),
    SUPER_ADMIN("최고 관리자", "ROLE_SUPER_ADMIN");

    private String role;
    private String roleValue;

    public static AdminRole fromRole(String role) {
        for(AdminRole adminRole : AdminRole.values()) {
            if(adminRole.role.equals(role)) {
                return adminRole;
            }
        }
        throw new MyException(
                JwtTokenError.IN_APPROPRIATE_TOKEN.getHttpStatus(),
                JwtTokenError.IN_APPROPRIATE_TOKEN.getMessage()
        );
    }
}

