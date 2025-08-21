package com.ecommercial.shopping.inventoryservice.inventory.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ProductIdAndAmount {
    private Long productId;
    private int amount;
}
