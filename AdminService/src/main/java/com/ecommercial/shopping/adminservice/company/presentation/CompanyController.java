package com.ecommercial.shopping.adminservice.company.presentation;

import com.ecommercial.shopping.adminservice.company.application.CompanyService;
import com.ecommercial.shopping.adminservice.company.application.dto.RegisterCompanyCommand;
import com.ecommercial.shopping.adminservice.company.presentation.dto.RegisterCompanyBodyModel;
import com.ecommercial.shopping.adminservice.global.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("companyController")
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<String>> registerCompany(@RequestBody RegisterCompanyBodyModel registerCompanyBodyModel) {
        System.out.println(registerCompanyBodyModel.getBusinessNumber());
        companyService.registerCompany(registerCompanyBodyModel.toDto());
        return ResponseEntity.ok(new BaseResponse<String>("OK","등록이 완료 됐습니다."));
    }


}
