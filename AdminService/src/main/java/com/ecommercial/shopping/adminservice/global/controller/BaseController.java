package com.ecommercial.shopping.adminservice.global.controller;

import com.ecommercial.shopping.adminservice.global.dto.BaseResponse;
import com.ecommercial.shopping.adminservice.global.exception.MyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseController {

    @ExceptionHandler(MyException.class)
    public ResponseEntity exceptionHandler(MyException e) {
        return ResponseEntity.status(e.getExceptionCode()).body(new BaseResponse("error", e.getMessage()));
    }
}
