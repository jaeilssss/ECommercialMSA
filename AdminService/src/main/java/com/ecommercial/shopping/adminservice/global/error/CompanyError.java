package com.ecommercial.shopping.adminservice.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CompanyError {
    IS_REGISTERED_COMPANY("이미 등록된 유저 입니다", HttpStatus.CONFLICT),
    NOT_FOUND_COMPANY_BY_ID("해당 ID는 존재하지 않습니다", HttpStatus.NOT_FOUND);
    private final String message;
    private final HttpStatus code;
}
