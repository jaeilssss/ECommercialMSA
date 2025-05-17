package com.ecommercial.shopping.adminservice.company.domain.util;

import com.ecommercial.shopping.adminservice.company.domain.entity.QCompany;
import com.querydsl.core.types.OrderSpecifier;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.function.Function;

@AllArgsConstructor
public enum CompanySortKey {
    NAME_ASC("name_asc", qCompany -> qCompany.companyName.asc()),
    NAME_DESC("name_dsc", qCompany -> qCompany.companyName.desc()),
    REGION_ASC("region_asc", qCompany -> qCompany.address.city.asc()),
    REGION_DESC("region_desc", qCompany -> qCompany.address.city.desc());

    private final String key;
    private final Function<QCompany, OrderSpecifier<?>> orderFunction;

    public static CompanySortKey fromKey(String key) {
        return Arrays.stream(values())
                .filter(v -> v.key.equalsIgnoreCase(key))
                .findFirst()
                .orElse(NAME_ASC);
    }

    public OrderSpecifier<?> getOrderSpecifier(QCompany qCompany) {
        return orderFunction.apply(qCompany);
    }

}
