package com.ecommercial.shopping.adminservice.company.domain.repository;

import com.ecommercial.shopping.adminservice.company.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository {
    public Optional<Company> save(Company company);
}
