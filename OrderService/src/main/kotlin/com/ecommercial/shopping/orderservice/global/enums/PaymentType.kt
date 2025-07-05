package com.ecommercial.shopping.orderservice.global.enums

enum class PaymentType(
    val type: String
) {
    CREDIT_CARD("카드결제"),
    BANK_TRANSFER("무통장 입금"),
    KAKAO_PAY("카카오페이 결제"),
    NAVER_PAY("네이버페이 결제")

}