package com.ecommercial.shopping.adminservice.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class MyException extends RuntimeException{
    private final String exceptionCode;
    private final String exceptionMessage;

    public MyException(String exceptionCode, String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
    }

}
