package com.ecommercial.shopping.productservice.product.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SearchProductCommand {


    @AllArgsConstructor
    @Getter
    public class Req {
        private String keyword;

    }



}
