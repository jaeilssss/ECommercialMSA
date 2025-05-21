package com.ecommercial.shopping.adminservice.category.application;

import com.ecommercial.shopping.adminservice.category.application.dto.RegisterCategoryCommand;
import com.ecommercial.shopping.adminservice.category.domain.entity.Category;

public interface CategoryService {

    public void registerCategory(RegisterCategoryCommand.Req request);
}
