package com.ecommercial.shopping.inventoryservice.inventory.application;

import com.ecommercial.shopping.inventoryservice.global.error.InventoryError;
import com.ecommercial.shopping.inventoryservice.global.exception.MyException;
import com.ecommercial.shopping.inventoryservice.inventory.application.dto.ReservedInventoryQuantityCommand;
import com.ecommercial.shopping.inventoryservice.inventory.application.dto.UpdateInventoryQuantityCommand;
import com.ecommercial.shopping.inventoryservice.inventory.domain.InventoryQueryRepository;
import com.ecommercial.shopping.inventoryservice.inventory.domain.InventoryRepository;
import com.ecommercial.shopping.inventoryservice.inventory.domain.entity.Inventory;
import com.ecommercial.shopping.inventoryservice.inventory.domain.vo.ProductIdAndAmount;
import com.ecommercial.shopping.model.UpdateProductStatusMessage;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryServiceImpl implements InventoryService{
    private final InventoryRepository inventoryRepository;
    private final InventoryQueryRepository inventoryQueryRepository;
    private final RedissonClient redissonClient;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    @Transactional
    public void updateInventoryQuantity(UpdateInventoryQuantityCommand.Req request) {
        Inventory inventory = getInventory(request.getProductId());
        inventory.updateInventoryQuantity(request.getInventoryQuantity());
    }

    @Transactional
    @Override
    public ReservedInventoryQuantityCommand.Res reservedProduct(ReservedInventoryQuantityCommand.Req request) {
        List<RLock> acquiredLocks = new ArrayList<>();
        HashMap<Long, Integer> result = new HashMap<>();
        List<UpdateProductStatusMessage.UpdateProductStatusDetail> outOfStockList = new ArrayList<>();
        List<UpdateProductStatusMessage.UpdateProductStatusDetail> updateProductStatusDetails = new ArrayList<>();

        try {
            for (ProductIdAndAmount productIdAndAmount : request.getProductIdAndAmounts()) {
                RLock lock = redissonClient.getLock("lock:product:" + productIdAndAmount.getProductId());
                boolean isLocked = lock.tryLock(300,3000, TimeUnit.MILLISECONDS);

                if (!isLocked) {
                    throw new RuntimeException("상품 [" + productIdAndAmount.getProductId() + "]은 현재 다른 사용자가 처리 중입니다.");
                }

                acquiredLocks.add(lock);
            }

            List<Inventory> inventoryList = inventoryQueryRepository.findByProductIdList(
                    request.getProductIdAndAmounts().stream()
                            .map(ProductIdAndAmount::getProductId)
                            .collect(Collectors.toList())
            );
            for (Inventory inventory : inventoryList) {
                int quantity = request.getProductIdAndAmounts().stream()
                        .filter(p -> p.getProductId().equals(inventory.getProductId()))
                        .map(ProductIdAndAmount::getAmount)
                        .findFirst().orElse(0);
                if (inventory.isReservedQuantity(quantity)) {
                    inventory.increaseReservedQuantity(quantity);
                    result.put(inventory.getId(), 0);

                    if (inventory.checkOutOfStock()) {
                        outOfStockList.add(createUpdateProductStatusDetail(inventory));
                    }
                } else {
                    result.put(inventory.getId(), inventory.getInventoryQuantity());
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("예약 실패: " + e.getMessage(), e);

        } finally {
            inventoryRepository.flush();
            for (int i = acquiredLocks.size()-1; i >= 0; i--) {
                acquiredLocks.get(i).unlock();
            }
        }

        sendMessageForUpdateProductStatus(outOfStockList);

        return ReservedInventoryQuantityCommand.Res
                .builder()
                .result(result)
                .build();
    }


    private Inventory getInventory(Long productId) {
        return inventoryQueryRepository.findByProductId(productId)
                .orElseThrow(
                        () -> new MyException(
                                InventoryError.NOT_FOUND_INVENTORY_BY_PRODUCT_ID.getCode(),
                                InventoryError.NOT_FOUND_INVENTORY_BY_PRODUCT_ID.getMessage()
                        )
                );
    }

    private UpdateProductStatusMessage.UpdateProductStatusDetail createUpdateProductStatusDetail(Inventory inventory) {
        return UpdateProductStatusMessage.UpdateProductStatusDetail.builder()
                .productId(inventory.getProductId())
                .amount(0)
                .build();
    }
    private void sendMessageForUpdateProductStatus(List<UpdateProductStatusMessage.UpdateProductStatusDetail> outOfStockList) {

        UpdateProductStatusMessage message = UpdateProductStatusMessage.builder().data(outOfStockList).build();
        kafkaTemplate.send(
                "update-product-status-false", message

        );

    }


}
