package com.ecommercial.shopping.adminservice.company.presentation;

import com.ecommercial.shopping.adminservice.company.application.dto.RegisterCompanyCommand;
import com.ecommercial.shopping.adminservice.company.presentation.dto.RegisterCompanyBodyModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CompanyDtoMapper {

    RegisterCompanyCommand.Req of(RegisterCompanyBodyModel registerCompanyBodyModel);
}
