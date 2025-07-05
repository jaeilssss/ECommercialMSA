package com.ecommercial.shopping.orderservice.global.error

import org.springframework.http.HttpStatus

enum class PaymentErrorEnum(
    val httpStatus: HttpStatus,
    val message: String
) {

    NOT_FOUND_PAYMENT_BY_ID(HttpStatus.NOT_FOUND, "해당 Payment id로 payment를 조회할 수 없습니다.")
}