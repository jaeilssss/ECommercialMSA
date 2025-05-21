package com.ecommercial.shopping.adminservice.category.application;

import com.ecommercial.shopping.adminservice.category.application.dto.RegisterCategoryCommand;
import com.ecommercial.shopping.adminservice.category.domain.entity.Category;
import com.ecommercial.shopping.adminservice.category.domain.repository.CategoryQueryRepository;
import com.ecommercial.shopping.adminservice.category.domain.repository.CategoryRepository;
import com.ecommercial.shopping.adminservice.global.error.CategoryError;
import com.ecommercial.shopping.adminservice.global.exception.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final CategoryQueryRepository categoryQueryRepository;

    @Transactional
    @Override
    public void registerCategory(RegisterCategoryCommand.Req request) {
        Category category = null;
        if (request.getParentCategory() != null) {
            category = getCategory(request.getParentCategory());
        }

        categoryRepository.save(request.toEntity(category));
    }


    private Category getCategory(Long categoryId) {
        return categoryQueryRepository.findById(categoryId)
                .orElseThrow(() -> new MyException(
                        CategoryError.NOT_FOUND_PARENT_CATEGORY.getHttpStatus(),
                        CategoryError.NOT_FOUND_PARENT_CATEGORY.getMessage()
                ));
    }
}
