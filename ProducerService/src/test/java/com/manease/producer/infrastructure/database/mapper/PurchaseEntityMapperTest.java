package com.manease.producer.infrastructure.database.mapper;

import com.manease.producer.domain.entity.Purchase;
import com.manease.producer.infrastructure.database.entity.PurchaseEntity;
import com.manease.producer.infrastructure.database.entity.PurchaseStatusEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseEntityMapperTest {
    private final PurchaseEntityMapper purchaseEntityMapper = new PurchaseEntityMapperImpl();

    @Test
    public void testMappingPurchaseToPurchaseEntity() {
        var purchaseId = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var statusId = UUID.randomUUID();

        var statusName = "Test purchase status name";
        var statusDescription = "Test purchase status description";
        var purchaseStatusEntity = new PurchaseStatusEntity(statusId, statusName, statusDescription);

        var purchase = new Purchase(purchaseId, producerId, statusId);
        var expectedPurchaseEntity = new PurchaseEntity(purchaseId, producerId, purchaseStatusEntity);
        var actualPurchaseEntity = purchaseEntityMapper.toPurchaseEntity(purchase, purchaseStatusEntity);

        assertThat(actualPurchaseEntity)
                .isNotNull()
                .isEqualTo(expectedPurchaseEntity);
    }

    @Test
    public void testMappingPurchaseEntityToPurchase() {
        var purchaseId = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var statusId = UUID.randomUUID();

        var statusName = "Test purchase status name";
        var statusDescription = "Test purchase status description";
        var purchaseStatusEntity = new PurchaseStatusEntity(statusId, statusName, statusDescription);
        
        var purchaseEntity = new PurchaseEntity(purchaseId, producerId, purchaseStatusEntity);
        var expectedPurchase = new Purchase(purchaseId, producerId, statusId);
        var actualPurchase = purchaseEntityMapper.toPurchase(purchaseEntity);

        assertThat(actualPurchase)
                .isNotNull()
                .isEqualTo(expectedPurchase);
    }
}
