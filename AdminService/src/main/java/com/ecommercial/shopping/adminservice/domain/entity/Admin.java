package com.ecommercial.shopping.adminservice.domain.entity;

import com.ecommercial.shopping.adminservice.global.enums.AdminRoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin")
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;
    private String birthday;
    private String phoneNumber;
    @Embedded
    private Address address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AdminRoleEnum role;

    @Embeddable
    public static class Address {
        private String city;
        private String firstAddress;
        private String secondAddress;
        private String zipCode;
    }

}
