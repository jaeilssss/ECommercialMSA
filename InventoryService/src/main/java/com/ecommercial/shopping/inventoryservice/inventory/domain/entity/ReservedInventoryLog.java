package com.ecommercial.shopping.inventoryservice.inventory.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReservedInventoryLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserved_inventory_log_id")
    private Long id;

    private Long productId;
    private int amount;
    
}
