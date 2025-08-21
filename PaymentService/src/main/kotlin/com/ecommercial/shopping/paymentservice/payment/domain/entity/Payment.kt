package com.ecommercial.shopping.paymentservice.payment.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "payment")
class Payment(

    @Id @GeneratedValue
    @Column(name = "pay_id")
    val id: Long,
    val userId: Long,
    val totalPrice: Int
) {
}