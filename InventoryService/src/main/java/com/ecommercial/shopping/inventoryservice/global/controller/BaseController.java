package com.ecommercial.shopping.inventoryservice.global.controller;


import com.ecommercial.shopping.inventoryservice.global.dto.BaseResponse;
import com.ecommercial.shopping.inventoryservice.global.exception.MyException;
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
