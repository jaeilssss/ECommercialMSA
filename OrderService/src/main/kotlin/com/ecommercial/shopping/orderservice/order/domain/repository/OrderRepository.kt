package com.ecommercial.shopping.orderservice.order.domain.repository

import com.ecommercial.shopping.orderservice.order.domain.entity.Order
import java.util.Optional

interface OrderRepository {
    fun save(order: Order): Optional<Order>

}