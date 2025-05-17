package com.ecommercial.shopping.adminservice.company.infrastructure;

import com.ecommercial.shopping.adminservice.company.domain.entity.Company;
import com.ecommercial.shopping.adminservice.company.domain.entity.QCompany;
import com.ecommercial.shopping.adminservice.company.domain.repository.CompanyQueryRepository;
import com.ecommercial.shopping.adminservice.company.domain.repository.CompanyRepository;
import com.ecommercial.shopping.adminservice.company.domain.util.CompanySortKey;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CompanyQueryRepositoryImpl implements CompanyQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private QCompany qCompany = QCompany.company;

    public CompanyQueryRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<Company> findByBusinessNumber(String businessNumber) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(qCompany)
                        .where(qCompany.businessNumber.eq(businessNumber))
                        .fetchOne()
        );
    }
    @Override
    public List<Company> findCompaniesBySortKey(String requestSortkey, int offset, int limit) {
        CompanySortKey sortKey = CompanySortKey.fromKey(requestSortkey);
        return jpaQueryFactory.selectFrom(qCompany)
                .orderBy(sortKey.getOrderSpecifier(QCompany.company))
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public Long findCompanySize(String requestSortKey) {
        return jpaQueryFactory
                .select(qCompany.count())
                .from(qCompany)
                .fetchOne();
    }

}
