package com.ecommercial.shopping.orderservice.global.controller

import com.ecommercial.shopping.orderservice.global.dto.BaseResponse
import com.ecommercial.shopping.orderservice.global.exception.ErrorException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BaseController {

    @ExceptionHandler(ErrorException::class)
    fun exceptionHandler(e: ErrorException): ResponseEntity<BaseResponse<String>> {
        val response = BaseResponse<String>("error", e.errorMessage)
        return ResponseEntity(response, e.httpStatus)
    }
}