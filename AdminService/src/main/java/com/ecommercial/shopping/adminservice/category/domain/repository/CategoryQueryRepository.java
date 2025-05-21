package com.ecommercial.shopping.adminservice.category.domain.repository;

import com.ecommercial.shopping.adminservice.category.domain.entity.Category;

import java.util.Optional;

public interface CategoryQueryRepository {
    Optional<Category> findById(Long id);
}
