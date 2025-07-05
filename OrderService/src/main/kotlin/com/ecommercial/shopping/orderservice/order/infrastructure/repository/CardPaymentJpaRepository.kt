package com.ecommercial.shopping.orderservice.order.infrastructure.repository

import com.ecommercial.shopping.orderservice.order.domain.entity.CreditPayment
import org.springframework.data.jpa.repository.JpaRepository

interface CardPaymentJpaRepository: JpaRepository<CreditPayment, Long> {
}