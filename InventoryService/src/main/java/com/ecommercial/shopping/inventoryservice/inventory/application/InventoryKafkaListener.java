package com.ecommercial.shopping.inventoryservice.inventory.application;

import com.ecommercial.shopping.inventoryservice.inventory.application.dto.kafka.TestOb;
import com.ecommercial.shopping.inventoryservice.inventory.domain.InventoryQueryRepository;
import com.ecommercial.shopping.inventoryservice.inventory.domain.InventoryRepository;
import com.ecommercial.shopping.inventoryservice.inventory.domain.entity.Inventory;
import com.ecommercial.shopping.model.NewInventoryUpdateMessage;
import com.ecommercial.shopping.model.UpdateProductCacheMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryKafkaListener {

    private final InventoryRepository inventoryRepository;
    private final InventoryQueryRepository inventoryQueryRepository;

    @KafkaListener(topics = "new-inventoryQuantity-update")
    public void updateInventoryQuantity(NewInventoryUpdateMessage message) {
        inventoryRepository.save(toInventory(
                message.getProductId(),
                message.getProductName(),
                message.getInventoryQuantity(),
                message.getCompanyName(),
                message.getCompanyId()
        ));
    }

    private Inventory toInventory(
            Long productId, String productName, int inventoryQuantity, String companyName, Long companyId
    ) {
        return Inventory.builder()
                .productId(productId)
                .productName(productName)
                .inventoryQuantity(inventoryQuantity)
                .companyName(companyName)
                .companyId(companyId)
                .build();
    }


}
