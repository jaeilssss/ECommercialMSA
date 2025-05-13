package com.ecommercial.shopping.adminservice.company.infrastructure;

import com.ecommercial.shopping.adminservice.company.domain.repository.CompanyRepository;
import com.ecommercial.shopping.adminservice.company.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyJpaRepository extends CompanyRepository, JpaRepository<Company, Long> {
}
