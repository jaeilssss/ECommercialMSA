package com.ecommercial.shopping.productservice.product.application.service;

import com.ecommercial.shopping.productservice.global.dto.AdminPrincipal;
import com.ecommercial.shopping.productservice.global.enums.AdminRole;
import com.ecommercial.shopping.productservice.global.error.ProductError;
import com.ecommercial.shopping.productservice.global.exception.MyException;
import com.ecommercial.shopping.productservice.product.application.dto.ProductInfoResponse;
import com.ecommercial.shopping.productservice.product.application.dto.RegisterProductCommand;
import com.ecommercial.shopping.productservice.product.application.dto.ReserveProductListCommand;
import com.ecommercial.shopping.productservice.product.domain.Product;
import com.ecommercial.shopping.productservice.product.domain.repository.ProductQueryRepository;
import com.ecommercial.shopping.productservice.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;


    @Override
    @Transactional
    public RegisterProductCommand.Res registerProduct(RegisterProductCommand.Req request) {
        Product product = productRepository.save(request.toEntity());

        return RegisterProductCommand.Res.toResponse(product);
    }

    @Override
    @Transactional
    public void deleteProduct(long productId) {
        AdminPrincipal principal = (AdminPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Product product = productQueryRepository.findById(productId)
                .orElseThrow(() -> new MyException(
                        ProductError.NOT_AUTHENTICATION_ACCOUNT.getHttpStatus(),
                        ProductError.NOT_FOUND_PRODUCT_ID.getMessage()
                ));

        checkRoleAuthentication(principal.getRole());
        checkCompanyAuthentication(product.getCompanyId(), principal.getCompanyId());

        product.setDelete(true);
    }

    @Override
    public ReserveProductListCommand.Res reserveProductList(ReserveProductListCommand.Req request) {
        List<Product> productList = productQueryRepository.findByIdList(request.getProductIdList());
        return null;
    }

    @Override
    public ProductInfoResponse getProduct(Long productId) {
        Product product = getProductById(productId);

        return ProductInfoResponse.fromEntity(product);
    }

    public Product getProductById(Long productId) {
        return productQueryRepository.findById(productId)
                .orElseThrow(() ->
                        new MyException(
                                ProductError.NOT_FOUND_PRODUCT_ID.getHttpStatus(),
                                ProductError.NOT_FOUND_PRODUCT_ID.getMessage()
                        )
                );
    }

    private void checkRoleAuthentication(AdminRole adminRole) {
        if (AdminRole.MANAGER != adminRole) {
            throw new MyException(
                    ProductError.NOT_AUTHENTICATION_ACCOUNT.getHttpStatus(),
                    ProductError.NOT_AUTHENTICATION_ACCOUNT.getMessage()
            );
        }
    }

    private void checkCompanyAuthentication(long productCompanyId, long adminCompanyId) {
        if (productCompanyId != adminCompanyId) {
            throw new MyException(
                    ProductError.NOT_FOUND_PRODUCT_ID.getHttpStatus(),
                    ProductError.NOT_FOUND_PRODUCT_ID.getMessage()
            );
        }
    }

}
