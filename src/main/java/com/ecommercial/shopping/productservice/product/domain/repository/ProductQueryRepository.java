package com.ecommercial.shopping.productservice.product.domain.repository;

import com.ecommercial.shopping.productservice.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductQueryRepository {
    public Optional<Product> findById(Long productId);
    public List<Product> findByIdList(List<Long> productIdList);
}
