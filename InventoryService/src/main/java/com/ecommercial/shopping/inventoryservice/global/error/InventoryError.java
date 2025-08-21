package com.ecommercial.shopping.inventoryservice.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum InventoryError {
    NOT_FOUND_INVENTORY_BY_PRODUCT_ID("해당 product id로 재고 데이터를 찾을 수 없습니다", HttpStatus.NOT_FOUND);

    private String message;
    private HttpStatus code;
}
