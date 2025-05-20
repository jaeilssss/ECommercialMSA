package com.ecommercial.shopping.adminservice.admin.infrastructure;

import com.ecommercial.shopping.adminservice.admin.domain.entity.Admin;
import com.ecommercial.shopping.adminservice.admin.domain.entity.QAdmin;
import com.ecommercial.shopping.adminservice.admin.domain.repository.AdminQueryRepository;
import com.ecommercial.shopping.adminservice.admin.domain.repository.AdminRepository;
import com.ecommercial.shopping.adminservice.company.domain.entity.QCompany;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AdminQueryRepositoryImpl implements AdminQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    QAdmin qAdmin = QAdmin.admin;
    QCompany qCompany = QCompany.company;
    public AdminQueryRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(qAdmin)
                        .where(qAdmin.email.eq(email))
                        .join(qCompany).fetchJoin()
                        .fetchOne()
        );
    }

    @Override
    public Optional<Admin> findById(Long id) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(qAdmin)
                        .where(qAdmin.id.eq(id))
                        .join(qCompany).fetchJoin()
                        .fetchOne()
        );
    }
}
