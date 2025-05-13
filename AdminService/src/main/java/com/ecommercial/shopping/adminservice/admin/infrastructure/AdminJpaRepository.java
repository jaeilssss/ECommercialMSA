package com.ecommercial.shopping.adminservice.admin.infrastructure;

import com.ecommercial.shopping.adminservice.admin.domain.entity.Admin;
import com.ecommercial.shopping.adminservice.admin.domain.repository.AdminRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminJpaRepository extends JpaRepository<Admin, Long>, AdminRepository {
}
