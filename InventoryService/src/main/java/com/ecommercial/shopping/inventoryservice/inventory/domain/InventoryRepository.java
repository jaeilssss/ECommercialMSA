package com.ecommercial.shopping.inventoryservice.inventory.domain;

import com.ecommercial.shopping.inventoryservice.inventory.domain.entity.Inventory;

public interface InventoryRepository {
    Inventory save(Inventory inventory);
    void flush();
}
