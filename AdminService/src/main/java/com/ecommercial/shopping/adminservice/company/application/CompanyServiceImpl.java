package com.ecommercial.shopping.adminservice.company.application;

import com.ecommercial.shopping.adminservice.company.application.dto.CompanyListCommand;
import com.ecommercial.shopping.adminservice.company.application.dto.RegisterCompanyCommand;
import com.ecommercial.shopping.adminservice.company.domain.repository.CompanyQueryRepository;
import com.ecommercial.shopping.adminservice.company.domain.repository.CompanyRepository;
import com.ecommercial.shopping.adminservice.global.error.CompanyError;
import com.ecommercial.shopping.adminservice.global.exception.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        System.out.println(request.getAddress().getFirstAddress());
        companyRepository.save(request.toEntity());
    }

    @Override
    public CompanyListCommand.Res getCompanyList(CompanyListCommand.Req request) {
        Long maxSize = companyQueryRepository.findCompanySize(request.getSortKey());
        List<CompanyListCommand.CompanyData> companyData = companyQueryRepository
                .findCompaniesBySortKey(
                        request.getSortKey(),
                        (request.getPage()-1)*request.getLimit(),
                        request.getLimit()
                )
                .stream()
                .map(CompanyListCommand.CompanyData::toCompanyData)
                .toList();

        return CompanyListCommand.Res.builder()
                .page(request.getPage())
                .limit(maxSize)
                .result(companyData)
                .build();
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