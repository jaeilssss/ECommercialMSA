package com.ecommercial.shopping.userservice.user.application.redis

interface UserRedisService {

    fun isCheckBlackList(accessToken: String): Boolean
    fun addBlackList(accessToken: String, expiration: Long)



}