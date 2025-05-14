package com.ecommercial.shopping.adminservice.company.presentation.dto;

import com.ecommercial.shopping.adminservice.admin.domain.vo.Address;
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
    private Address address;
}
