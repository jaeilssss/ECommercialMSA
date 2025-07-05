package com.ecommercial.shopping.orderservice.order.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.BatchSize
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
@Builder
@EntityListeners(AuditingEntityListener::class)
class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    val id: Long = 0,
    val userId: Long,
    val totalPrice: Int,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val orderItems: List<OrderItem>


) {

    @CreatedDate
    @Column(updatable = false)
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime

}