package com.ecommercial.shopping.adminservice.admin.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class AdminUserLoginCommand {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Req {
        private String email;
        private String password;
    }
}
