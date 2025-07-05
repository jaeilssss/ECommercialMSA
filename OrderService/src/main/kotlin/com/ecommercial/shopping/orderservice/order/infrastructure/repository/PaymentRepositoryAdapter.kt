package com.ecommercial.shopping.orderservice.order.infrastructure.repository

import com.ecommercial.shopping.orderservice.order.domain.entity.Payment
import com.ecommercial.shopping.orderservice.order.domain.repository.PaymentRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class PaymentRepositoryAdapter(val repository: PaymentJpaRepository): PaymentRepository {
    override fun save(payment: Payment): Optional<Payment> {
        return Optional.ofNullable(repository.save(payment))
    }


}