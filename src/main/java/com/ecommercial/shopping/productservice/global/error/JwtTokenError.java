package com.ecommercial.shopping.productservice.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum JwtTokenError {
    IN_APPROPRIATE_TOKEN("적절하지 않은 토큰의 정보 입니다.", HttpStatus.UNAUTHORIZED);

    private final String message;
    private final HttpStatus httpStatus;
}
