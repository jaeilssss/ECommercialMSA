package com.ecommercial.shopping.adminservice.admin.presentation.dto;

import com.ecommercial.shopping.adminservice.admin.application.dto.AdminSignUpCommend;
import com.ecommercial.shopping.adminservice.admin.domain.vo.Address;
import com.ecommercial.shopping.adminservice.global.enums.AdminRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class AdminSignUpBody {
    private String email;
    private String password;
    private String name;
    private String birthday;
    private Long companyId;
    private Address address;
    private String phoneNumber;
    private AdminRoleEnum adminRoleEnum;
    public AdminSignUpCommend.Req toDto() {
        return AdminSignUpCommend.Req.builder()
                .email(email)
                .password(password)
                .name(name)
                .birthday(birthday)
                .companyId(companyId)
                .phoneNumber(phoneNumber)
                .address(address)
                .adminRoleEnum(adminRoleEnum)
                .build();
    }
}
