package com.ecommercial.shopping.orderservice.order.domain.repository

import com.ecommercial.shopping.orderservice.order.domain.entity.Payment
import java.util.*

interface PaymentQueryRepository {

    fun findByPaymentData(paymentId: Long): Optional<Payment>
}