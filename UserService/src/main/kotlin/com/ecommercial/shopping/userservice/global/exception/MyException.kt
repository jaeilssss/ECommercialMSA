package com.ecommercial.shopping.userservice.global.exception

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.springframework.http.HttpStatus

@AllArgsConstructor
@NoArgsConstructor
class MyException(
    val httpSecurity: HttpStatus,
    val exceptionMessage: String
) : RuntimeException(){
}