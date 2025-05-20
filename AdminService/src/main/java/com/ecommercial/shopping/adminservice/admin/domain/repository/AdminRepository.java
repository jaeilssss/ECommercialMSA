package com.ecommercial.shopping.adminservice.admin.domain.repository;

import com.ecommercial.shopping.adminservice.admin.domain.entity.Admin;

import java.util.Optional;

public interface AdminRepository {
    Admin save(Admin admin);
}
