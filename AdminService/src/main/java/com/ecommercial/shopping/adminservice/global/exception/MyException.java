package com.ecommercial.shopping.adminservice.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MyException extends RuntimeException{
    private final HttpStatus exceptionCode;
    private final String exceptionMessage;

    public MyException(HttpStatus exceptionCode, String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
    }

}
