package com.ecommercial.shopping.orderservice.order.application

import com.ecommercial.shopping.orderservice.order.application.dto.PayRequest
import com.ecommercial.shopping.orderservice.order.application.dto.PaymentInfoResponse

interface PaymentService {

    fun pay(request: PayRequest)
    fun getPaymentInfo(paymentId: Long): PaymentInfoResponse
}