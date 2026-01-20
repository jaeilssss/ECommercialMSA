package com.ecommercial.shopping.productservice.product.application.dto;

import com.ecommercial.shopping.productservice.product.application.listener.dto.ElasticSearchProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;


@Builder
@AllArgsConstructor
public class SearchProductResponse {
//    private List<SearchProduct> data;

//    public static SearchProductResponse to(List<ElasticSearchProduct> elasticSearchProducts) {
//        return SearchProductResponse.builder()
//                .data(elasticSearchProducts.stream()
//                        .map(ElasticSearchProduct::to)
//                        .collect(Collectors.toList())
//                )
//                .build();
//    }


}
