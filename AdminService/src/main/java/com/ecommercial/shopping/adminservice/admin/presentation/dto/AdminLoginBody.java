package com.ecommercial.shopping.adminservice.admin.presentation.dto;

import com.ecommercial.shopping.adminservice.admin.application.dto.AdminUserLoginCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminLoginBody {
    private String email;
    private String password;

    public AdminUserLoginCommand.Req toDto() {
        return AdminUserLoginCommand.Req.builder()
                .email(email)
                .password(password)
                .build();
    }
}
