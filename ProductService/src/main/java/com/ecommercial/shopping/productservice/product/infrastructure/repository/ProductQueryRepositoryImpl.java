package com.ecommercial.shopping.productservice.product.infrastructure;

import com.ecommercial.shopping.productservice.product.domain.Product;
import com.ecommercial.shopping.productservice.product.domain.QProduct;
import com.ecommercial.shopping.productservice.product.domain.repository.ProductQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductQueryRepositoryImpl implements ProductQueryRepository {

    private JPAQueryFactory jpaQueryFactory;
    QProduct qProduct = QProduct.product;
    public ProductQueryRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(qProduct)
                        .where(qProduct.id.eq(productId))
                        .fetchOne()
        );
    }

    @Override
    public List<Product> findByIdList(List<Long> productIdList) {
        return jpaQueryFactory.selectFrom(qProduct)
                .where(qProduct.id.in(productIdList))
                .fetch();
    }
}
