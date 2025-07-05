package com.ecommercial.shopping.orderservice.global.error

import org.springframework.http.HttpStatus

enum class OrderErrorEnum(
    val httpStatus: HttpStatus,
    val message: String
) {

    NOT_FOUND_ORDER_ID(HttpStatus.NOT_FOUND, "orderId에 맞는 order가 존재 하지 않습니다.")
}