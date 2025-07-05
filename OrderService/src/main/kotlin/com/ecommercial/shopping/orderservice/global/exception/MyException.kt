package com.ecommercial.shopping.orderservice.global.exception

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.springframework.http.HttpStatus

@AllArgsConstructor
@NoArgsConstructor
class MyException(
    val httpSecurity: HttpStatus,
    override val message: String
) : RuntimeException(message){
}