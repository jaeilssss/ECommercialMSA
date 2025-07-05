package com.ecommercial.shopping.orderservice.global.enums

enum class PaymentStatus(
    val status: String
) {

    COMPLETE("결제 완료"),
    REFUND("환불 완료")

}