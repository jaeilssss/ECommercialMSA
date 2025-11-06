package com.ecommercial.shopping.productservice.global.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class BaseResponse<T> {
    public String code;
    public T data;
}
