package com.ecommercial.shopping.orderservice.order.domain.repository

import com.ecommercial.shopping.orderservice.order.domain.entity.Payment
import java.util.Optional

interface PaymentRepository {
    fun save(payment: Payment): Optional<Payment>
}