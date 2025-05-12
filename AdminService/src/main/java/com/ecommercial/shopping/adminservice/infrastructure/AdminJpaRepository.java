package com.ecommercial.shopping.adminservice.infrastructure;

import com.ecommercial.shopping.adminservice.domain.entity.Admin;
import com.ecommercial.shopping.adminservice.domain.repository.AdminRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminJpaRepository extends JpaRepository<Admin, Long>, AdminRepository {
}
