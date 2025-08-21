package com.ecommercial.shopping.productservice.product.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class ReserveProductListCommand {


    @AllArgsConstructor
    @Getter
    public static class Req {
        List<Long> productIdList;
    }

    @AllArgsConstructor
    @Getter
    public static class Res {
        List<Boolean> resultList;
    }
}
