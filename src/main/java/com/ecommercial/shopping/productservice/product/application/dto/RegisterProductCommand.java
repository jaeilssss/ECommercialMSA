package com.ecommercial.shopping.productservice.product.application.dto;


import com.ecommercial.shopping.productservice.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class RegisterProductCommand {

    @AllArgsConstructor
    @Builder
    @Getter
    public static class Req {
        private String productName;
        private String brandName;

        private int price;
        private Long companyId;
        private String categoryName;
        private Long categoryId;
        private int quantity;
        private Long registerAdminId;

        public Product toEntity() {
            return Product.builder()
                    .brandName(brandName)
                    .productName(productName)
                    .price(price)
                    .status(quantity > 0)
                    .companyId(companyId)
                    .categoryId(categoryId)
                    .categoryName(categoryName)
                    .build();
        }
    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class Res {
        private Long id;

        private String productName;
        private String brandName;

        private int price;

        private Long companyId;
        private String companyName;

        private Long categoryId;
        private String categoryName;

        public static Res toResponse(Product product) {
            return Res.builder()
                    .id(product.getId())
                    .productName(product.getProductName())
                    .brandName(product.getBrandName())
                    .price(product.getPrice())
                    .companyId(product.getCompanyId())
                    .companyName(product.getCompanyName())
                    .categoryId(product.getCategoryId())
                    .categoryName(product.getCategoryName())
                    .build();
        }
    }
}
