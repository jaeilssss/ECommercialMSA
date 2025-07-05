package com.ecommercial.shopping.orderservice.order.infrastructure.repository

import com.ecommercial.shopping.orderservice.order.domain.repository.CardQueryRepository
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class CardQueryRepositoryImpl(entityManager: EntityManager): CardQueryRepository {
}