package com.ecommercial.shopping.inventoryservice.inventory.application.dto;

import com.ecommercial.shopping.inventoryservice.inventory.domain.vo.ProductIdAndAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

public class ReservedInventoryQuantityCommand {

    @AllArgsConstructor
    @Getter
    @Builder
    public static class Req {
        private List<ProductIdAndAmount> productIdAndAmounts;
    }

    @AllArgsConstructor
    @Getter
    @Builder
    public static class Res {
        HashMap<Long, Integer> result;
    }

}
