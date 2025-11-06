package com.ecommercial.shopping.productservice.product.infrastructure.repository;

import com.ecommercial.shopping.productservice.product.domain.ProductStatusHistory;
import com.ecommercial.shopping.productservice.product.domain.repository.ProductStatusHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductStatusHistoryRepositoryAdapter implements ProductStatusHistoryRepository {
    private final ProductStatusHistoryJpaRepository repository;


    @Override
    public ProductStatusHistory save(ProductStatusHistory productStatusHistory) {
        return repository.save(productStatusHistory);
    }

    @Override
    public List<ProductStatusHistory> saveAll(List<ProductStatusHistory> entities) {
        System.out.println("saveALL");
        return repository.saveAll(entities);
    }
}
