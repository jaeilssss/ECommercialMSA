package com.ecommercial.shopping.adminservice.category.infrastructure;

import com.ecommercial.shopping.adminservice.category.domain.entity.Category;
import com.ecommercial.shopping.adminservice.category.domain.repository.CategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositoryImpl extends JpaRepository<Category, Long>, CategoryRepository {
}
