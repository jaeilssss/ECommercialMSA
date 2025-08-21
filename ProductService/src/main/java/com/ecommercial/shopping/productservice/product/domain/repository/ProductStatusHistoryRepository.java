package com.ecommercial.shopping.productservice.product.domain.repository;

import com.ecommercial.shopping.productservice.product.domain.ProductStatusHistory;

import java.util.List;

public interface ProductStatusHistoryRepository {

    ProductStatusHistory save(ProductStatusHistory productStatusHistory);
    List<ProductStatusHistory> saveAll(List<ProductStatusHistory> entities);
}
