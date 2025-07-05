package com.ecommercial.shopping.inventoryservice.inventory.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

@Entity
@Table(name = "inventory")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Inventory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long id;

    private Long productId;
    private String productName;
    private Long CompanyId;
    private String companyName;

    private int inventoryQuantity;


    public void increaseInventoryQuantity(int amount) {
        this.inventoryQuantity += amount;
    }

    public void decreaseInventoryQuantity(int amount) {
        this.inventoryQuantity -= amount;
    }

    public boolean isDecreaseInventoryQuantity(int amount) {
        return this.inventoryQuantity - amount > 0;
    }

}
