package com.ecommercial.shopping.orderservice.global.dto

class BaseResponse<T>(
    val code : String,
    val data: T
){
}