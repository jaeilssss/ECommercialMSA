package com.ecommercial.shopping.adminservice.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum CategoryError {
    NOT_FOUND_PARENT_CATEGORY("부모 카테고리를 찾을 수 없습니다", HttpStatus.NOT_FOUND);

    private String message;

    private HttpStatus httpStatus;



}
