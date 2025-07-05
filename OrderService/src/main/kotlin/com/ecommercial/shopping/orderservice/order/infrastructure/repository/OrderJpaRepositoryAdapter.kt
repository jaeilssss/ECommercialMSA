package com.ecommercial.shopping.orderservice.order.infrastructure.repository

import com.ecommercial.shopping.orderservice.order.domain.entity.Order
import com.ecommercial.shopping.orderservice.order.domain.repository.OrderRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class OrderJpaRepositoryAdapter(val repository: OrderJpaRepository): OrderRepository {
    override fun save(order: Order): Optional<Order> {
        return Optional.ofNullable(repository.save(order))
    }

}