package com.ecommercial.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class NewInventoryUpdateMessage {

    private Long productId;
    private String productName;

    private Long companyId;
    private String companyName;

    private int inventoryQuantity;


}
