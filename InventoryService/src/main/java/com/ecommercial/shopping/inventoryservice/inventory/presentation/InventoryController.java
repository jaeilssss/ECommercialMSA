package com.ecommercial.shopping.inventoryservice.inventory.presentation;

import com.ecommercial.shopping.inventoryservice.global.dto.BaseResponse;
import com.ecommercial.shopping.inventoryservice.inventory.application.InventoryService;
import com.ecommercial.shopping.inventoryservice.inventory.application.dto.ReservedInventoryQuantityCommand;
import com.ecommercial.shopping.inventoryservice.inventory.presentation.dto.ReservedInventoryQuantityBody;
import com.ecommercial.shopping.inventoryservice.inventory.presentation.dto.UpdateInventoryQuantityBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/update")
    public ResponseEntity<BaseResponse<String>> updateInventoryQuantity(
            @RequestBody UpdateInventoryQuantityBody inventoryBody
    ) {
        inventoryService.updateInventoryQuantity(inventoryBody.toDto());

        return ResponseEntity.ok(new BaseResponse<>("OK", "재고 업데이트가 완료했습니다."));
    }

    @PostMapping("/reserved")
    public ResponseEntity<BaseResponse<ReservedInventoryQuantityCommand.Res>> reservedProduct(
            @RequestBody ReservedInventoryQuantityBody reservedInventoryQuantityBody
    ) {
        System.out.println(reservedInventoryQuantityBody.getProductIdAndAmounts().get(0).getAmount());
        return ResponseEntity.ok(new BaseResponse<>(
                "OK",
                inventoryService.reservedProduct(reservedInventoryQuantityBody.toDto())
        ));
    }

}

