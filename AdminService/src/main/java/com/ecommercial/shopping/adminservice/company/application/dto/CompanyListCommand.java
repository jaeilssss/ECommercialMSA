package com.ecommercial.shopping.adminservice.company.application.dto;

import com.ecommercial.shopping.adminservice.admin.domain.vo.Address;
import com.ecommercial.shopping.adminservice.company.domain.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CompanyListCommand {

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Req {
        private String sortKey;
        private int page;
        private int limit;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class Res {
        private int page;
        private Long limit;
        private List<CompanyData> result;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class CompanyData {
        private Long companyId;
        private String companyName;
        private String phoneNumber;
        private String businessNumber;
        private Address address;

        public static CompanyData toCompanyData(Company company) {
            return CompanyData.builder()
                    .companyId(company.getId())
                    .companyName(company.getCompanyName())
                    .phoneNumber(company.getPhoneNumber())
                    .businessNumber(company.getBusinessNumber())
                    .address(company.getAddress())
                    .build();
        }
    }
}
