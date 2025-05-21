package com.ecommercial.shopping.adminservice.category.infrastructure;

import com.ecommercial.shopping.adminservice.category.domain.entity.Category;
import com.ecommercial.shopping.adminservice.category.domain.entity.QCategory;
import com.ecommercial.shopping.adminservice.category.domain.repository.CategoryQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CategoryQueryRepositoryImpl implements CategoryQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    QCategory qCategory = QCategory.category;
    public CategoryQueryRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<Category> findById(Long id) {
        QCategory parent = new QCategory("parent");
        QCategory children = new QCategory("children");
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(qCategory)
                        .leftJoin(qCategory.parent,parent).fetchJoin()
                        .leftJoin(qCategory.children, children).fetchJoin()
                        .where(qCategory.id.eq(id))
                        .fetchOne()
        );
    }
}
