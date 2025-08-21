package com.ecommercial.shopping.inventoryservice.inventory.infrastructure;

import com.ecommercial.shopping.inventoryservice.inventory.domain.InventoryRepository;
import com.ecommercial.shopping.inventoryservice.inventory.domain.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryJpaRepository extends JpaRepository<Inventory, Long>, InventoryRepository {
}
