package com.ecommercial.shopping.adminservice.company.infrastructure;

import com.ecommercial.shopping.adminservice.company.domain.entity.Company;
import com.ecommercial.shopping.adminservice.company.domain.entity.QCompany;
import com.ecommercial.shopping.adminservice.company.domain.repository.CompanyQueryRepository;
import com.ecommercial.shopping.adminservice.company.domain.repository.CompanyRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class CompanyQueryRepositoryImpl implements CompanyQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private QCompany qCompany = QCompany.company;

    public CompanyQueryRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
        this.entityManager = entityManager;
//        try {
//            Connection connection = entityManager.unwrap(Connection.class);
//            DatabaseMetaData metaData = connection.getMetaData();
//            System.out.println(">>> Connected to DB: " + metaData.getURL());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public Optional<Company> findByBusinessNumber(String businessNumber) {
        printCurrentDatabaseUrl();
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(qCompany)
                        .where(qCompany.businessNumber.eq(businessNumber))
                        .fetchOne()
        );
    }

    private void printCurrentDatabaseUrl() {
        try {
            Session session = entityManager.unwrap(Session.class);

            // session.connection() 은 Hibernate 6.x 부터 제거됨 → work() 로 접근해야 함
            session.doWork(connection -> {
                try {
                    DatabaseMetaData metaData = connection.getMetaData();
                    System.out.println(">>> Connected to DB: " + metaData.getURL());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
