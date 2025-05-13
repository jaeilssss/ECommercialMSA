package com.ecommercial.shopping.adminservice.company.infrastructure;

import com.ecommercial.shopping.adminservice.company.domain.repository.CompanyRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CompanyQueryRepositoryImpl implements CompanyRepository{
    private final JPAQueryFactory jpaQueryFactory;


}
