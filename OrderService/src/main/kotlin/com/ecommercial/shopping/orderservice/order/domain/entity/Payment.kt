package com.ecommercial.shopping.orderservice.order.domain.entity

import com.ecommercial.shopping.orderservice.global.enums.PaymentStatus
import com.ecommercial.shopping.orderservice.global.enums.PaymentType
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "payment")
@EntityListeners(AuditingEntityListener::class)
class Payment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    val order: Order,

    val price: Int,

    val userId: Long,

    @Enumerated(EnumType.STRING)
    val paymentType: PaymentType,

    @Enumerated(EnumType.STRING)
    val paymentStatus: PaymentStatus,

    @ManyToOne
    @JoinColumn(name = "banktransfer_payment_id")
    val bankTransferPayment: BankTransferPayment?,

    @ManyToOne
    @JoinColumn(name = "creditpayment_payment_id")
    val creditPayment: CreditPayment?

) {

    @CreatedDate
    @Column(updatable = false)
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime

}