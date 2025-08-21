package com.ecommercial.shopping.userservice.global.dto
class BaseResponse<T>(
    val message : String,
    val data: T
){
}