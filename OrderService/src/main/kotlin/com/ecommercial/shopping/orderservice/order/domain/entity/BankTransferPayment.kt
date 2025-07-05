package com.ecommercial.shopping.orderservice.order.domain.entity

import com.ecommercial.shopping.orderservice.global.enums.PaymentStatus
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@EntityListeners(AuditingEntityListener::class)
class BankTransferPayment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_transfer_payment_id")
    val id: Long = 0,
    val accountNumber: String,
    val bankName: String,
    val userId: Long,
    val status: Boolean,
    @Enumerated
    val paymentStatus: PaymentStatus
) {
}