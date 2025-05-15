package com.ecommercial.shopping.adminservice.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CompanyError {
    IS_REGISTERED_COMPANY("이미 등록된 유저 입니다", "COMPANY_001");
    private final String message;
    private final String code;
}
