package com.ecommercial.shopping.productservice.product.application.listener;

import com.ecommercial.shopping.model.UpdateProductStatusMessage;
import com.ecommercial.shopping.productservice.product.domain.Product;
import com.ecommercial.shopping.productservice.product.domain.ProductStatusHistory;
import com.ecommercial.shopping.productservice.product.domain.repository.ProductQueryRepository;
import com.ecommercial.shopping.productservice.product.domain.repository.ProductStatusHistoryRepository;
import com.ecommercial.shopping.productservice.product.infrastructure.repository.ProductStatusHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductInventoryListener {

    private final ProductQueryRepository productQueryRepository;
    private final ProductStatusHistoryRepository productStatusHistoryRepository;
    private final ProductStatusHistoryJpaRepository repository;

    @Transactional
    @KafkaListener(topics = "update-product-status-false", groupId = "inventory-group")
    public void updateProductStatusFalse(UpdateProductStatusMessage message) {
        List<Long> productIdList = message.getData().stream()
                .map(UpdateProductStatusMessage.UpdateProductStatusDetail::getProductId)
                .toList();
        List<Product> productList = getProductList(productIdList);

        productList.forEach(p-> p.updateStatus(false));

        List<ProductStatusHistory> productStatusHistories = productList.stream()
                .map(this::createProductStatusHistory)
                .toList();

        productStatusHistoryRepository.saveAll(productStatusHistories);

    }

    private List<Product> getProductList(List<Long> productIdList) {
        return productQueryRepository.findByIdList(productIdList);
    }

    private ProductStatusHistory createProductStatusHistory(Product product) {
        return ProductStatusHistory.builder()
                .productId(product.getId())
                .beforeStatus(!product.isStatus())
                .afterStatus(product.isStatus())
                .build();
    }
}
