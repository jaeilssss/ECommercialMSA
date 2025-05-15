package com.ecommercial.shopping.adminservice.company.application;

import com.ecommercial.shopping.adminservice.company.application.dto.RegisterCompanyCommand;
import com.ecommercial.shopping.adminservice.company.domain.entity.Company;
import com.ecommercial.shopping.adminservice.company.domain.repository.CompanyQueryRepository;
import com.ecommercial.shopping.adminservice.company.domain.repository.CompanyRepository;
import com.ecommercial.shopping.adminservice.global.error.CompanyError;
import com.ecommercial.shopping.adminservice.global.exception.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyQueryRepository companyQueryRepository;


    @Transactional
    @Override
    public void registerCompany(RegisterCompanyCommand.Req request) {
        isRegisteredCompany(request.getBusinessNumber());
        companyRepository.save(request.toEntity());
    }

    @Transactional(readOnly = true)
    public void isRegisteredCompany(String businessNumber) {
        companyQueryRepository.findByBusinessNumber(businessNumber)
                .ifPresent(company -> {
                    throw new MyException(
                            CompanyError.IS_REGISTERED_COMPANY.getCode(),
                            CompanyError.IS_REGISTERED_COMPANY.getMessage()
                    );
                });
    }
}