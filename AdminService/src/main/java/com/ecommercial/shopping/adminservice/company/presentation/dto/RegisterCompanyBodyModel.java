package com.ecommercial.shopping.adminservice.company.presentation.dto;

import com.ecommercial.shopping.adminservice.admin.domain.vo.Address;
import com.ecommercial.shopping.adminservice.company.application.dto.RegisterCompanyCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCompanyBodyModel {
    private String companyName;
    private String ceoName;
    private String phoneNumber;
    private String businessNumber;
    private Address address;

    public RegisterCompanyCommand.Req toDto() {
        return RegisterCompanyCommand.Req.builder()
                .companyName(companyName)
                .ceoName(ceoName)
                .phoneNumber(phoneNumber)
                .businessNumber(businessNumber)
                .address(address)
                .build();
    }
}
