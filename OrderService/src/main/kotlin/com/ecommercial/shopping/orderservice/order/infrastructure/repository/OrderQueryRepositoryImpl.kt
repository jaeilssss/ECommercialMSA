package com.ecommercial.shopping.orderservice.order.infrastructure.repository

import com.ecommercial.shopping.orderservice.order.domain.entity.Order
import com.ecommercial.shopping.orderservice.order.domain.entity.QOrder
import com.ecommercial.shopping.orderservice.order.domain.entity.QOrderItem
import com.ecommercial.shopping.orderservice.order.domain.repository.OrderQueryRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class OrderQueryRepositoryImpl(entityManager: EntityManager): OrderQueryRepository {
    private val jpaQueryRepository : JPAQueryFactory = JPAQueryFactory(entityManager)
    private val qOrder = QOrder.order
    private val qOrderItem = QOrderItem.orderItem
    override fun findById(orderId: Long): Optional<Order> {
        return Optional.ofNullable(
            jpaQueryRepository.selectFrom(qOrder)
                .where(qOrder.id.eq(orderId))
                .leftJoin(qOrder.orderItems, qOrderItem)
                .fetchOne()
        )
    }
}