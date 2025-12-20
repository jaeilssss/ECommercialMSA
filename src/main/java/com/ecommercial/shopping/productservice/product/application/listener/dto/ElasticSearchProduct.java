package com.ecommercial.shopping.productservice.product.application.listener.dto;

import com.ecommercial.shopping.model.UpdateElasticSearchProductMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ElasticSearchProduct {
    private Long productId;
    private String productName;
    private String companyName;
    private String categoryName;
    private int price;


    public static ElasticSearchProduct of(UpdateElasticSearchProductMessage message) {
        return ElasticSearchProduct.builder()
                .productId(message.getProductId())
                .productName(message.getProductName())
                .companyName(message.getCompanyName())
                .categoryName(message.getCategoryName())
                .price(message.getPrice())
                .build();
    }
}
