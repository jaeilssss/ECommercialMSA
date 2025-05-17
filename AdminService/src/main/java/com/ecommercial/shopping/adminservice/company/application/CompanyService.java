package com.ecommercial.shopping.adminservice.company.application;

import com.ecommercial.shopping.adminservice.company.application.dto.CompanyListCommand;
import com.ecommercial.shopping.adminservice.company.application.dto.RegisterCompanyCommand;

import java.util.List;

public interface CompanyService {
    public void registerCompany(RegisterCompanyCommand.Req request);
    public CompanyListCommand.Res getCompanyList(CompanyListCommand.Req request);

}
