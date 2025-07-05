package com.ecommercial.shopping.orderservice.order.infrastructure.repository

import com.ecommercial.shopping.orderservice.order.domain.entity.BankTransferPayment
import org.springframework.data.jpa.repository.JpaRepository

interface BankTransferJpaRepository: JpaRepository<BankTransferPayment, Long> {
}