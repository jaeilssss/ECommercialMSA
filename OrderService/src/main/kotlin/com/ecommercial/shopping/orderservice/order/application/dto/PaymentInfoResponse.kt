package com.ecommercial.shopping.orderservice.order.application.dto

import com.ecommercial.shopping.orderservice.global.enums.PaymentStatus
import com.ecommercial.shopping.orderservice.global.enums.PaymentType
import com.ecommercial.shopping.orderservice.order.domain.entity.Payment
import java.time.LocalDateTime

class PaymentInfoResponse(
    val payDate: LocalDateTime,
    val price: Int,
    val orderItemsInfoResponseList: List<OrderItemsInfoResponse>,
    val userId: Long,
    val paymentType: PaymentType,
    val paymentStatus: PaymentStatus,
    val paymentPayInfoResponse: PaymentPayInfoResponse
) {

    companion object {
        fun create(payment: Payment): PaymentInfoResponse {
            val orderItemList = payment.order.orderItems.map { OrderItemsInfoResponse.create(it) }
            return PaymentInfoResponse(
                payDate = payment.createdAt,
                price = payment.price,
                orderItemsInfoResponseList = orderItemList,
                userId = payment.userId,
                paymentType = payment.paymentType,
                paymentStatus = payment.paymentStatus,
                paymentPayInfoResponse = PaymentPayInfoResponse.create(payment.creditPayment, payment.bankTransferPayment)
            )
        }
    }
}