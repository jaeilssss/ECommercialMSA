package com.ecommercial.shopping.userservice.user.domain.entity

import com.ecommercial.shopping.userservice.global.enums.Grade
import com.ecommercial.shopping.userservice.user.domain.vo.Address
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener::class)
class User() { // ✅ 기본 생성자 (파라미터 없는 생성자)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Long? = null

    var name: String = ""
    var email: String = ""
    var birthDay: String = ""
    var password: String = ""

    @Embedded
    var address: Address = Address()

    var phoneNumber: String = ""
    var isDeleted: Boolean = false
    var grade: Grade = Grade.BRONZE

    // ✅ 전체 필드 받는 생성자
    constructor(
        id: Long? = null,
        name: String,
        email: String,
        birthDay: String,
        password: String,
        address: Address,
        phoneNumber: String,
        isDeleted: Boolean = false,
        grade: Grade
    ) : this() {
        this.id = id
        this.name = name
        this.email = email
        this.birthDay = birthDay
        this.password = password
        this.address = address
        this.phoneNumber = phoneNumber
        this.isDeleted = isDeleted
        this.grade = grade
    }
}