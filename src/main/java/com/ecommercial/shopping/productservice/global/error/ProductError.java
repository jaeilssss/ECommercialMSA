package com.ecommercial.shopping.productservice.global.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ProductError {
    NOT_AUTHENTICATION_ACCOUNT("삭제할 권한이 없는 계정 입니다", HttpStatus.BAD_REQUEST),
    NOT_FOUND_PRODUCT_ID("상품이 존재 하지 않습니다", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus httpStatus;

}
