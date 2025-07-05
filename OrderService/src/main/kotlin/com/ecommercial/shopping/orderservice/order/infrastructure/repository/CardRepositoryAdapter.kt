package com.ecommercial.shopping.orderservice.order.infrastructure.repository

import com.ecommercial.shopping.orderservice.order.domain.entity.CreditPayment
import com.ecommercial.shopping.orderservice.order.domain.repository.CardRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CardRepositoryAdapter(val repository: CardPaymentJpaRepository): CardRepository {
    override fun save(cardPayment: CreditPayment): Optional<CreditPayment> {
        return Optional.ofNullable(
            repository.save(cardPayment)
        )
    }
}