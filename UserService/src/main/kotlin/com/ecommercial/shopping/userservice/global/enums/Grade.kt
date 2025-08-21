package com.ecommercial.shopping.userservice.global.enums

enum class Grade(
    val gradeName: String,
    val gradeLevel: Int
) {

    BRONZE("BRONZE", 1),
    SILVER("SILVER",2),
    GOLD("GOLD",3),
    PLATINUM("PLATINUM",4),
    VIP("VIP",5)
}