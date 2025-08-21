package com.ecommercial.shopping.inventoryservice.inventory.domain;

import com.ecommercial.shopping.inventoryservice.inventory.domain.entity.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryQueryRepository {
    Optional<Inventory> findByProductId(Long productId);
    List<Inventory> findByProductIdList(List<Long> productList);

}
