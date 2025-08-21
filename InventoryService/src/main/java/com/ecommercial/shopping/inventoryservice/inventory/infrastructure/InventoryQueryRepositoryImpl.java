package com.ecommercial.shopping.inventoryservice.inventory.infrastructure;

import com.ecommercial.shopping.inventoryservice.inventory.domain.InventoryQueryRepository;
import com.ecommercial.shopping.inventoryservice.inventory.domain.entity.Inventory;
import com.ecommercial.shopping.inventoryservice.inventory.domain.entity.QInventory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InventoryQueryRepositoryImpl implements InventoryQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    QInventory qInventory = QInventory.inventory;
    public InventoryQueryRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);

    }

    @Override
    public Optional<Inventory> findByProductId(Long productId) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(qInventory)
                        .where(qInventory.companyId.eq(productId))
                        .fetchOne()
        );
    }

    @Override
    public List<Inventory> findByProductIdList(List<Long> productList) {
        return jpaQueryFactory.selectFrom(qInventory)
                .where(qInventory.productId.in(productList))
                .fetch();
    }
}
