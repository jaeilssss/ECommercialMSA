package com.ecommercial.shopping.orderservice.order.domain.vo

class CardPaymentDto(
    val cardNumber: String,
    val userName: String,
    val installment: Int,
    val cardCompany: String
) {
}