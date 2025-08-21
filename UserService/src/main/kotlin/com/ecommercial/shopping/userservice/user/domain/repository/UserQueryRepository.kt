package com.ecommercial.shopping.userservice.user.domain.repository

import com.ecommercial.shopping.userservice.user.domain.entity.User
import java.util.Optional

interface UserQueryRepository {
    public fun findByEmail(email: String) : Optional<User>
    public fun findById(userId: Long) : Optional<User>
}