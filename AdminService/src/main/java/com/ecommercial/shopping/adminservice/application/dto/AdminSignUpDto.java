package com.ecommercial.shopping.adminservice.application.dto;

import com.ecommercial.shopping.adminservice.domain.entity.Admin;
import lombok.Getter;
import lombok.Setter;

public class AdminSignUpDto {


    @Getter
    @Setter
    public static class Req {
        private String email;
        private String password;
        private String name;
        private String birthday;
        private String city;
        private String firstAddress;
        private String secondAddress;
        private String zipCode;
    }
}