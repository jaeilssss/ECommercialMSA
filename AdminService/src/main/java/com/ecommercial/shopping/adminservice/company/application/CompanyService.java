package com.ecommercial.shopping.adminservice.company.application;

import com.ecommercial.shopping.adminservice.company.application.dto.RegisterCompanyCommand;

public interface CompanyService {
    public void registerCompany(RegisterCompanyCommand.Req request);

}
