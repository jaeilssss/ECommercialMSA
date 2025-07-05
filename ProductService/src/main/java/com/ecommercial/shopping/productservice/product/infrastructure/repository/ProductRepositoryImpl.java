package com.ecommercial.shopping.productservice.product.infrastructure;

import com.ecommercial.shopping.productservice.product.domain.Product;
import com.ecommercial.shopping.productservice.product.domain.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryImpl extends JpaRepository<Product, Long>, ProductRepository {
}
