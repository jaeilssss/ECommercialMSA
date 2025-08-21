package com.ecommercial.shopping.inventoryservice.inventory.presentation.dto;

import com.ecommercial.shopping.inventoryservice.inventory.application.dto.ReservedInventoryQuantityCommand;
import com.ecommercial.shopping.inventoryservice.inventory.domain.vo.ProductIdAndAmount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservedInventoryQuantityBody {

    private List<ProductIdAndAmount> productIdAndAmounts;

    public ReservedInventoryQuantityCommand.Req toDto(){
        return ReservedInventoryQuantityCommand.Req.builder()
                .productIdAndAmounts(productIdAndAmounts)
                .build();
    }

}
