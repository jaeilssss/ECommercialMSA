package com.ecommercial.shopping.adminservice.category.presentation.dto;


import com.ecommercial.shopping.adminservice.category.application.dto.RegisterCategoryCommand;
import lombok.Getter;

@Getter
public class RegisterCategoryBody {
    private String name;
    private Long parentCategory;

    public RegisterCategoryCommand.Req toDto() {
        return RegisterCategoryCommand.Req.builder()
                .name(name)
                .parentCategory(parentCategory)
                .build();
    }
}
