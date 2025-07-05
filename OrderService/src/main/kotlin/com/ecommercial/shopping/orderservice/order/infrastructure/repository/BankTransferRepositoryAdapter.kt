package com.ecommercial.shopping.orderservice.order.infrastructure.repository

import com.ecommercial.shopping.orderservice.order.domain.entity.BankTransferPayment
import com.ecommercial.shopping.orderservice.order.domain.repository.BankTransferRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
class BankTransferRepositoryAdapter(val repository: BankTransferJpaRepository): BankTransferRepository {
    override fun save(bankTransferPayment: BankTransferPayment): Optional<BankTransferPayment> {
        return Optional.ofNullable(
            repository.save(bankTransferPayment)
        )
    }
}