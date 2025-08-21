package com.ecommercial.shopping.userservice.user.application

import com.ecommercial.shopping.userservice.global.error.enums.UserException
import com.ecommercial.shopping.userservice.global.exception.MyException
import com.ecommercial.shopping.userservice.global.jwt.JwtProviders
import com.ecommercial.shopping.userservice.user.application.dto.request.LoginRequest
import com.ecommercial.shopping.userservice.user.application.dto.request.SignUpRequest
import com.ecommercial.shopping.userservice.user.application.dto.response.JwtTokenResponse
import com.ecommercial.shopping.userservice.user.application.dto.response.UserInfoResponse
import com.ecommercial.shopping.userservice.user.application.redis.UserRedisService
import com.ecommercial.shopping.userservice.user.domain.entity.User
import com.ecommercial.shopping.userservice.user.domain.repository.UserQueryRepository
import com.ecommercial.shopping.userservice.user.domain.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service

class UserServiceImpl(
    val userRepository: UserRepository,
    val userQueryRepository: UserQueryRepository,
    val passwordEncoder: PasswordEncoder,
    val redisService: UserRedisService,
    val jwtProviders: JwtProviders
): UserService {

    override fun signUp(request: SignUpRequest) {
        isEmail(request.email)

        userRepository.save(request.toEntity(encodedPassword(request.password)))
    }

    override fun login(request: LoginRequest): JwtTokenResponse {
        val user = getUserByEmail(request.email)

        checkPassword(request.password, user.password)

        return jwtProviders.createToken(user)

    }

    override fun getMyInfo(userId: Long): UserInfoResponse {
        val user = getUserByUserId(userId)

        return UserInfoResponse.fromEntity(user)
    }

    override fun logout(accessToken: String) {
        redisService.addBlackList(accessToken, jwtProviders.getExpirationFromToken(accessToken))
    }


    private fun encodedPassword(rawPassword: String): String = passwordEncoder.encode(rawPassword)

    private fun checkPassword(rawPassword: String, encodedPassword: String) = passwordEncoder.matches(
        rawPassword, encodedPassword
    )
    private fun isEmail(email: String) {
        userRepository.findByEmail(email)
            .ifPresent {
                throw MyException(
                    UserException.ALREADY_JOIN_EMAIL.httpStatus,
                    UserException.ALREADY_JOIN_EMAIL.message
                )
            }
    }

    private fun getUserByEmail(email: String) : User {
        return userQueryRepository.findByEmail(email)
            .orElseThrow {
                MyException(
                    UserException.IS_NOT_FOUND_USER_BY_EMAIL.httpStatus,
                    UserException.IS_NOT_FOUND_USER_BY_EMAIL.message
                )
            }
    }

    private fun getUserByUserId(id: Long) : User {
        return userQueryRepository.findById(id)
            .orElseThrow {
                MyException(
                    UserException.IS_NOT_FOUND_USER_BY_ID.httpStatus,
                    UserException.IS_NOT_FOUND_USER_BY_ID.message
                )
            }
    }
}