package com.ecommercial.shopping.adminservice.global.dto;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseResponse<T> {
    private String code;
    private T data;
}
