package com.ecommercial.shopping.adminservice.admin.application.dto;

import com.ecommercial.shopping.adminservice.admin.domain.entity.Admin;
import com.ecommercial.shopping.adminservice.admin.domain.vo.Address;
import com.ecommercial.shopping.adminservice.company.domain.entity.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class AdminSignUpCommend {


    @Getter
    @Setter
    @Builder
    public static class Req {
        private String email;
        private String password;
        private String name;
        private String birthday;
        private Long companyId;
        private Address address;

        public Admin toEntity(Company company, String encodedPassword) {
            return Admin.builder()
                    .email(email)
                    .company(company)
                    .password(encodedPassword)
                    .name(name)
                    .birthday(birthday)
                    .address(address)
                    .build();
        }
    }
}