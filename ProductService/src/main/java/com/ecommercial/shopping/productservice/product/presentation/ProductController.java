package com.ecommercial.shopping.productservice.product.presentation;

import com.ecommercial.shopping.model.NewInventoryUpdateMessage;
import com.ecommercial.shopping.model.UpdateProductCacheMessage;
import com.ecommercial.shopping.productservice.global.dto.BaseResponse;
import com.ecommercial.shopping.productservice.product.application.dto.ProductInfoResponse;
import com.ecommercial.shopping.productservice.product.application.dto.RegisterProductCommand;
import com.ecommercial.shopping.productservice.product.application.service.ProductService;
import com.ecommercial.shopping.productservice.product.presentation.dto.RegisterProductBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<String>> registerProduct(RegisterProductBody registerProductBody) {
        RegisterProductCommand.Res response = productService.registerProduct(registerProductBody.toDto());
        NewInventoryUpdateMessage message = NewInventoryUpdateMessage.builder()
                .productName(response.getProductName())
                .productId(response.getId())
                .companyId(response.getCompanyId())
                .inventoryQuantity(registerProductBody.getInventoryQuantity())
                .companyName(response.getCompanyName())
                .build();
        kafkaTemplate.send("new-inventoryQuantity-update", message);
        kafkaTemplate.send("new-orderservice-caching", createUpdateProductCacheMessage(response));
        return ResponseEntity.ok(new BaseResponse<>("OK","상품 등록이 완료 됐습니다."));
    }

    @PutMapping("/delete")
    public ResponseEntity<BaseResponse<String>> deleteProduct(@RequestParam("productId") Long productId) {
        productService.deleteProduct(productId);

        return ResponseEntity.ok(new BaseResponse<>("OK", "상품 삭제가 완료 했습니다."));
    }

    @GetMapping("/get/product/info/{productId}")
    public ResponseEntity<BaseResponse<ProductInfoResponse>> getProductInfo(@PathVariable Long productId) {
        System.out.println("------------------API 호출-----------------------");
        System.out.println("------------------API 종료-----------------------");
        return ResponseEntity.ok(
                new BaseResponse<>("OK", productService.getProduct(productId))
        );

    }

    private UpdateProductCacheMessage createUpdateProductCacheMessage(RegisterProductCommand.Res res) {
        return UpdateProductCacheMessage.builder()
                .productName(res.getProductName())
                .productId(res.getId())
                .amount(0)
                .categoryId(res.getCategoryId())
                .categoryName(res.getCategoryName())
                .price(res.getPrice())
                .companyId(res.getCompanyId())
                .companyName(res.getCompanyName())
                .build();
    }
}
