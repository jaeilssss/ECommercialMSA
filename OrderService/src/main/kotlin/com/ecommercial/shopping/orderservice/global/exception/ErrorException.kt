package com.ecommercial.shopping.orderservice.global.exception

import org.springframework.http.HttpStatus

class ErrorException(
    val httpStatus: HttpStatus,
    val errorMessage : String
) : RuntimeException(){

}