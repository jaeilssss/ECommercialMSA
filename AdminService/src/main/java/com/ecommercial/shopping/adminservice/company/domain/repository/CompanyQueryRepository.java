package com.ecommercial.shopping.adminservice.company.domain.repository;

import com.ecommercial.shopping.adminservice.company.domain.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyQueryRepository {
    Optional<Company> findByBusinessNumber(String businessNumber);
    List<Company> findCompaniesBySortKey(String requestSortkey, int offset, int limit);
    Long findCompanySize(String requestSortKey);

}
