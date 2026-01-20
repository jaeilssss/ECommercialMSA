package com.ecommercial.shopping.productservice.product.domain.repository;

import com.ecommercial.shopping.productservice.product.application.listener.dto.ElasticSearchProduct;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;


public interface ElasticSearchProductRepository {
    SearchHits<ElasticSearchProduct> findByProductName(String keyword);
}
