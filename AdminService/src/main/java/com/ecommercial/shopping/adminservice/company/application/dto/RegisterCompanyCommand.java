package com.ecommercial.shopping.adminservice.company.application.dto;

import com.ecommercial.shopping.adminservice.admin.domain.vo.Address;
import com.ecommercial.shopping.adminservice.company.domain.entity.Company;
import lombok.Getter;
import lombok.Setter;

public class RegisterCompanyCommand {

    @Getter
    @Setter
    public static class Req {
        private String companyName;
        private String ceoName;
        private String phoneNumber;
        private Address address;

        public Company toEntity() {
            return Company.builder()
                    .companyName(companyName)
                    .ceoName(ceoName)
                    .phoneNumber(phoneNumber)
                    .address(address)
                    .build();
        }
    }

}
