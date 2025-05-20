package com.ecommercial.shopping.adminservice.admin.domain.repository;

import com.ecommercial.shopping.adminservice.admin.domain.entity.Admin;

import java.util.Optional;

public interface AdminQueryRepository {
    Optional<Admin> findByEmail(String email);
    Optional<Admin> findById(Long id);
}
