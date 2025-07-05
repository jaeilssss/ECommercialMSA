package com.ecommercial.shopping.orderservice.order.presentation.dto

import com.ecommercial.shopping.orderservice.global.enums.PaymentStatus
import com.ecommercial.shopping.orderservice.global.enums.PaymentType
import com.ecommercial.shopping.orderservice.order.application.dto.PayRequest
import com.ecommercial.shopping.orderservice.order.domain.vo.BankTransferDto
import com.ecommercial.shopping.orderservice.order.domain.vo.CardPaymentDto

class PaymentBody(
    val userId: Long,
    val orderId: Long,
    val price: Int,
    val paymentType: PaymentType,
    val cardPayInfo: CardPaymentDto? = null,
    val bankTransferInfo: BankTransferDto? = null,
    val paymentStatus: PaymentStatus
) {
    fun toDto() : PayRequest {
        return PayRequest(
            userId = userId,
            orderId = orderId,
            price = price,
            paymentType = paymentType,
            cardPayInfo = cardPayInfo,
            bankTransferInfo = bankTransferInfo,
            paymentStatus = paymentStatus
        )
    }
}