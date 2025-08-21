package com.ecommercial.shopping.inventoryservice.inventory.application;

import com.ecommercial.shopping.inventoryservice.inventory.application.dto.ReservedInventoryQuantityCommand;
import com.ecommercial.shopping.inventoryservice.inventory.application.dto.UpdateInventoryQuantityCommand;

public interface InventoryService {

    public void updateInventoryQuantity(UpdateInventoryQuantityCommand.Req request);

    public ReservedInventoryQuantityCommand.Res reservedProduct(ReservedInventoryQuantityCommand.Req request);

}
