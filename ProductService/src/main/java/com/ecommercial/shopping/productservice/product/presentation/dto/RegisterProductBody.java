package com.ecommercial.shopping.productservice.product.presentation.dto;


import com.ecommercial.shopping.productservice.product.application.dto.RegisterProductCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterProductBody {
    private String productName;
    private String brandName;

    private int price;
    private int inventoryQuantity;
    private Long companyId;

    private Long categoryId;
    private String categoryName;

    public RegisterProductCommand.Req toDto() {
        return RegisterProductCommand.Req.builder()
                .productName(productName)
                .brandName(brandName)
                .price(price)
                .quantity(inventoryQuantity)
                .companyId(companyId)
                .categoryId(categoryId)
                .categoryName(categoryName)
                .build();
    }


}
