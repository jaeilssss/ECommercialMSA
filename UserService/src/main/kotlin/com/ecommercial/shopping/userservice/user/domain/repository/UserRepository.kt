package com.ecommercial.shopping.userservice.user.domain.repository

import com.ecommercial.shopping.userservice.user.domain.entity.User
import java.util.Optional

public interface UserRepository {
    fun findByEmail(email: String) : Optional<User>
    fun save(user: User) : User

}