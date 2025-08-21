package com.ecommercial.shopping.userservice.global.controller

import com.ecommercial.shopping.userservice.global.dto.BaseResponse
import com.ecommercial.shopping.userservice.global.error.ErrorException
import com.ecommercial.shopping.userservice.global.exception.MyException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BaseController {

    @ExceptionHandler(MyException::class)
    fun exceptionHandler(e: MyException): ResponseEntity<BaseResponse<String>> {
        val response = BaseResponse<String>("error", e.exceptionMessage)
        return ResponseEntity(response, e.httpSecurity)
    }
}