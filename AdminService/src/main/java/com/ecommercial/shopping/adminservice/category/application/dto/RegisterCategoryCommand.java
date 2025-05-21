package com.ecommercial.shopping.adminservice.category.application.dto;

import com.ecommercial.shopping.adminservice.category.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class RegisterCategoryCommand {

    @AllArgsConstructor
    @Builder
    @Getter
    public static class Req {
        private String name;
        private Long parentCategory;

        public Category toEntity(Category parent) {
            return Category.builder()
                    .name(name)
                    .parent(parent)
                    .build();
        }
    }
}
