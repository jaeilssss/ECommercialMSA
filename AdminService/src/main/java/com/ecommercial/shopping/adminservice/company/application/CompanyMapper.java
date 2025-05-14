package com.ecommercial.shopping.adminservice.company.application;

import com.ecommercial.shopping.adminservice.company.application.dto.RegisterCompanyCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CompanyMapper {
}
