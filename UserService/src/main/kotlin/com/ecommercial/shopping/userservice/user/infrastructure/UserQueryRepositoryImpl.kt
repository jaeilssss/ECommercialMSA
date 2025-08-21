package com.ecommercial.shopping.userservice.user.infrastructure

import com.ecommercial.shopping.userservice.user.domain.entity.QUser
import com.ecommercial.shopping.userservice.user.domain.entity.User
import com.ecommercial.shopping.userservice.user.domain.repository.UserQueryRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserQueryRepositoryImpl (entityManager: EntityManager): UserQueryRepository {
    private val jpaQueryRepository : JPAQueryFactory = JPAQueryFactory(entityManager)
    private val qUser: QUser = QUser.user
    override fun findByEmail(email: String): Optional<User> {
        return Optional.ofNullable(
            jpaQueryRepository.selectFrom(qUser)
                .where(qUser.email.eq(email))
                .fetchOne()
        )
    }

    override fun findById(userId: Long): Optional<User> {
        return Optional.ofNullable(
            jpaQueryRepository.selectFrom(qUser)
                .where(qUser.id.eq(userId))
                .fetchOne()
        )
    }
}