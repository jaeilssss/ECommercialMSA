package com.ecommercial.shopping.userservice.user.infrastructure

import com.ecommercial.shopping.userservice.user.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserJpaRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String) : Optional<User>
}