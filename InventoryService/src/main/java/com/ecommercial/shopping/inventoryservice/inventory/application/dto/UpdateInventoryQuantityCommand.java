package com.ecommercial.shopping.inventoryservice.inventory.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UpdateInventoryQuantityCommand {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class Req {
        private Long productId;
        private int inventoryQuantity;
    }

}
