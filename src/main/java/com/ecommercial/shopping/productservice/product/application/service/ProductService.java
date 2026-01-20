package com.ecommercial.shopping.productservice.product.application.service;

import com.ecommercial.shopping.productservice.product.application.dto.*;
import com.ecommercial.shopping.productservice.product.application.listener.dto.ElasticSearchProduct;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    public RegisterProductCommand.Res registerProduct(RegisterProductCommand.Req request);
    public void deleteProduct(long productId);
    public ReserveProductListCommand.Res reserveProductList(ReserveProductListCommand.Req request);

    public ProductInfoResponse getProduct(Long productId);

    public List<ElasticSearchProduct> search(String keyword) throws IOException;
}
