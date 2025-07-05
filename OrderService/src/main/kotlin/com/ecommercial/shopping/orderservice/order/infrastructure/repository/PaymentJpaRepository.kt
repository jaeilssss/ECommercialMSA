package com.ecommercial.shopping.orderservice.order.infrastructure.repository

import com.ecommercial.shopping.orderservice.order.domain.entity.Payment
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentJpaRepository: JpaRepository<Payment, Long> {
}