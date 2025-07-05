package com.ecommercial.shopping.orderservice.order.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table(name = "order_items")
@Builder
class OrderItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    val id: Long = 0,
    val productId: Long,
    val productName: String,
    val companyId: Long,
    val price: Int,
    val categoryId: Long,
    val categoryName: String,

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = true)
    var order: Order? = null,
) {
    @CreatedDate
    @Column(updatable = false)
    lateinit var createdDate: LocalDateTime
    @LastModifiedDate
    lateinit var updatedDate: LocalDateTime

}