package com.ecommercial.shopping.userservice.user.infrastructure

import com.ecommercial.shopping.userservice.global.error.ErrorException
import com.ecommercial.shopping.userservice.user.domain.entity.User
import com.ecommercial.shopping.userservice.user.domain.repository.UserRepository
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import lombok.NonNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository
import java.util.*

@AllArgsConstructor
@NoArgsConstructor
@Repository
class UserRepositoryAdapter(val repository: UserJpaRepository) : UserRepository {
    override fun findByEmail(email: String): Optional<User> {
        return repository.findByEmail(email)
    }

    override fun save(user: User): User {
        return repository.save(user)
    }

}