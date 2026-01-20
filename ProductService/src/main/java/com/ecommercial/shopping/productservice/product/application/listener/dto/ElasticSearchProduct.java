package com.ecommercial.shopping.productservice.product.application.listener.dto;

import com.ecommercial.shopping.model.UpdateElasticSearchProductMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@Builder
@Getter
@Document(indexName = "product")
public class ElasticSearchProduct {
    @Id
    private Long productId;
    private String productName;
    private String companyName;
    private String categoryName;
    private Integer price;


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
