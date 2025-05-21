package com.ecommercial.shopping.adminservice.category.presentation;

import com.ecommercial.shopping.adminservice.category.application.CategoryService;
import com.ecommercial.shopping.adminservice.category.presentation.dto.RegisterCategoryBody;
import com.ecommercial.shopping.adminservice.global.dto.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;


    @PostMapping("/register")
    public ResponseEntity<BaseResponse<String>> registerCategory(
            @RequestBody RegisterCategoryBody registerCategoryBody
    ) {
        categoryService.registerCategory(registerCategoryBody.toDto());

        return ResponseEntity.ok(new BaseResponse<>("OK","카테고리 등록이 완료 됐습니다"));
    }

}
