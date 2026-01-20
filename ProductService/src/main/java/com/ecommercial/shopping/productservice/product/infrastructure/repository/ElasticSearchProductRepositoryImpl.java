package com.ecommercial.shopping.productservice.product.infrastructure.repository;

import com.ecommercial.shopping.productservice.product.application.listener.dto.ElasticSearchProduct;
import com.ecommercial.shopping.productservice.product.domain.repository.ElasticSearchProductRepository;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface ElasticSearchProductRepositoryImpl extends ElasticSearchProductRepository, ElasticsearchRepository<ElasticSearchProduct, Long> {

    @Query("""
{
  "multi_match": {
    "query": "?0",
    "fields": [
      "productName^3",
      "brandName^2",
      "productName.autocomplete",
      "brandName.autocomplete"
    ]
  }
}
""")
    SearchHits<ElasticSearchProduct> findByProductName(String keyword);

}
