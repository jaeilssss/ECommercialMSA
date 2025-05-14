package com.ecommercial.shopping.adminservice.company.application;

import com.ecommercial.shopping.adminservice.company.application.dto.RegisterCompanyCommand;
import com.ecommercial.shopping.adminservice.company.domain.repository.CompanyQueryRepository;
import com.ecommercial.shopping.adminservice.company.domain.repository.CompanyRepository;
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
        companyRepository.save(request.toEntity());
    }
}