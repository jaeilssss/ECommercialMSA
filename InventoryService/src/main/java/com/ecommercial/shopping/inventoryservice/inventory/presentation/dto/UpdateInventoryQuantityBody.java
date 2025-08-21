package com.ecommercial.shopping.inventoryservice.inventory.presentation.dto;

import com.ecommercial.shopping.inventoryservice.inventory.application.dto.UpdateInventoryQuantityCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateInventoryQuantityBody {
    private Long productId;
    private int inventoryQuantity;

    public UpdateInventoryQuantityCommand.Req toDto() {
        return UpdateInventoryQuantityCommand.Req.builder()
                .productId(productId)
                .inventoryQuantity(inventoryQuantity)
                .build();
    }
}
