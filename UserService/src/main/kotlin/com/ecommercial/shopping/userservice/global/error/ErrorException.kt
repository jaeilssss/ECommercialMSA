package com.ecommercial.shopping.userservice.global.error

import org.springframework.http.HttpStatus

class ErrorException(
    val httpStatus: HttpStatus,
    val errorMessage : String
) : RuntimeException(){

}