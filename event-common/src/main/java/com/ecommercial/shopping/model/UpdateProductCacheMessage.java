package com.ecommercial.shopping.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class UpdateProductCacheMessage {
    private Long productId;
    private String productName;
    private String companyName;
    private Long companyId;
    private Long categoryId;
    private String categoryName;
    private int price;
    private int amount;
}
