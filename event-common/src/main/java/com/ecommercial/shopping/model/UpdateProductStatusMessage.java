package com.ecommercial.shopping.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateProductStatusMessage {
    private List<UpdateProductStatusDetail> data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UpdateProductStatusDetail {
        private Long productId;
        private int amount;
    }
}
