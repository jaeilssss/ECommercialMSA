package com.ecommercial.shopping.adminservice.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AdminError {
    IS_SIGNUP_EMAIL("이미 등록된 이메일 입니다", HttpStatus.CONFLICT),
    NOT_FOUND_EMAIL("해당 이메일을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NOT_AVAILABLE_LOGIN("로그인 실패 ", HttpStatus.BAD_REQUEST);
    private final String message;
    private final HttpStatus code;
}
