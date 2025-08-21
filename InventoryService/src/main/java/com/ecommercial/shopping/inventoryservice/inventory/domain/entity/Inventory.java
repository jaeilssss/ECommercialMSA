package com.ecommercial.shopping.inventoryservice.inventory.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import org.springframework.security.core.parameters.P;

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
    private Long companyId;
    private String companyName;

    private int inventoryQuantity;
    private int reservedQuantity;


    public void increaseInventoryQuantity(int amount) {
        this.inventoryQuantity += amount;
    }

    public void decreaseInventoryQuantity(int amount) {
        this.inventoryQuantity -= amount;
    }

    public void increaseReservedQuantity(int amount) {
        this.reservedQuantity += amount;
        System.out.println(this.reservedQuantity);
    }
    public void decreaseReservedQuantity(int amount) {
        this.reservedQuantity -= amount;
    }
    public boolean isDecreaseInventoryQuantity(int amount) {
        return this.inventoryQuantity - amount >= 0;
    }

    public boolean isReservedQuantity(int amount) {
        return this.inventoryQuantity-this.reservedQuantity-amount >= 0;
    }

    public boolean checkOutOfStock() {
        return this.inventoryQuantity == 0 || this.reservedQuantity == this.inventoryQuantity;
    }
    public void updateInventoryQuantity(int inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

}
