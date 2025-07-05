package com.ecommercial.shopping.orderservice.order.domain.entity

import com.ecommercial.shopping.orderservice.global.enums.PaymentStatus
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
class CreditPayment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_payment_id")
    val id: Long = 0,
    val userId: Long,
    val cardCompany: String,
    val cardNumber: String,
    val installment: Int,
    val status: Boolean,
    @Enumerated
    val paymentStatus: PaymentStatus
) {

    @CreatedDate
    @Column(updatable = false)
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime



}