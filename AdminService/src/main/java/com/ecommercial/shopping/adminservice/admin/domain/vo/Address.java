package com.ecommercial.shopping.adminservice.admin.domain.vo;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;
    private String firstAddress;
    private String secondAddress;
    private String zipCode;
}