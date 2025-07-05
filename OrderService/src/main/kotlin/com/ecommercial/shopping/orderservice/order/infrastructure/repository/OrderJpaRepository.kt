package com.ecommercial.shopping.orderservice.order.infrastructure.repository

import com.ecommercial.shopping.orderservice.order.domain.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository: JpaRepository<Order, Long> {
}