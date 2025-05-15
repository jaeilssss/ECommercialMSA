package com.ecommercial.shopping.adminservice.company.domain.repository;

import com.ecommercial.shopping.adminservice.company.domain.entity.Company;

import java.util.Optional;

public interface CompanyQueryRepository {
    Optional<Company> findByBusinessNumber(String businessNumber);
}
