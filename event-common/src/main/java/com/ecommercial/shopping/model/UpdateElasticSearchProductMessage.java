package com.ecommercial.shopping.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateElasticSearchProductMessage {
    private Long productId;
    private String productName;
    private String companyName;
    private String categoryName;
    private int price;
}
