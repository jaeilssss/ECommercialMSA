package com.ecommercial.shopping.productservice.product.infrastructure;

import com.ecommercial.shopping.productservice.product.domain.ProductStatusHistory;
import com.ecommercial.shopping.productservice.product.domain.repository.ProductStatusHistoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStatusHistoryJpaRepository extends JpaRepository<ProductStatusHistory, Long>, ProductStatusHistoryRepository {
}
