package com.ecommercial.shopping.orderservice.order.infrastructure.repository

import com.ecommercial.shopping.orderservice.order.domain.entity.Payment
import com.ecommercial.shopping.orderservice.order.domain.entity.QBankTransferPayment
import com.ecommercial.shopping.orderservice.order.domain.entity.QCreditPayment
import com.ecommercial.shopping.orderservice.order.domain.entity.QPayment
import com.ecommercial.shopping.orderservice.order.domain.repository.PaymentQueryRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
class PaymentQueryRepositoryImpl(entityManager: EntityManager): PaymentQueryRepository {
    private val jpaQueryFactory : JPAQueryFactory = JPAQueryFactory(entityManager)
    private val qPayment: QPayment = QPayment.payment
    private val qCreditPayment: QCreditPayment = QCreditPayment.creditPayment
    private val qBankTransferPayment: QBankTransferPayment = QBankTransferPayment.bankTransferPayment

    override fun findByPaymentData(paymentId: Long): Optional<Payment> {
        return Optional.ofNullable(
            jpaQueryFactory.selectFrom(qPayment)
                .where(qPayment.id.eq(paymentId))
                .join(qPayment.creditPayment, qCreditPayment).fetchJoin()
                .join(qPayment.bankTransferPayment, qBankTransferPayment).fetchJoin()
                .fetchOne()
        )
    }

}