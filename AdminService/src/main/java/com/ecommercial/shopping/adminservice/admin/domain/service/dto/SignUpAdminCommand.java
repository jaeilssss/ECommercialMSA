package com.ecommercial.shopping.adminservice.admin.domain.service.dto;

import lombok.*;

public class SignUpAdminCommand {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Req {
        private String name;
    }
}
