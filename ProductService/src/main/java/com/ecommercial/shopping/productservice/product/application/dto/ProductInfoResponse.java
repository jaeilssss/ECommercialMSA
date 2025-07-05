package com.ecommercial.shopping.productservice.product.application.dto;

import com.ecommercial.shopping.productservice.product.domain.Product;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoResponse {
    private Long id;

    private String productName;
    private String brandName;

    private int price;

    private Long companyId;
    private String companyName;

    private Long categoryId;
    private String categoryName;

    private boolean isDelete;

    private boolean status;

    public static ProductInfoResponse fromEntity(Product product) {
        return ProductInfoResponse.builder()
                .brandName(product.getBrandName())
                .id(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .companyId(product.getCompanyId())
                .companyName(product.getCompanyName())
                .categoryId(product.getCategoryId())
                .categoryName(product.getCategoryName())
                .isDelete(product.isDelete())
                .status(product.isStatus())
                .build();
    }
}
