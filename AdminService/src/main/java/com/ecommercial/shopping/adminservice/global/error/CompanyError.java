package com.ecommercial.shopping.adminservice.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CompanyError {
    IS_REGISTERED_COMPANY("이미 등록된 유저 입니다", HttpStatus.CONFLICT);
    private final String message;
    private final HttpStatus code;
}
