package com.ecommercial.shopping.productservice.product.application.service;

import com.ecommercial.shopping.productservice.product.application.dto.ProductInfoResponse;
import com.ecommercial.shopping.productservice.product.application.dto.RegisterProductCommand;
import com.ecommercial.shopping.productservice.product.application.dto.ReserveProductListCommand;

public interface ProductService {

    public RegisterProductCommand.Res registerProduct(RegisterProductCommand.Req request);
    public void deleteProduct(long productId);
    public ReserveProductListCommand.Res reserveProductList(ReserveProductListCommand.Req request);

    public ProductInfoResponse getProduct(Long productId);
}
