package com.manease.producer.infrastructure.database.mapper;

import com.manease.producer.domain.entity.PurchaseStatus;
import com.manease.producer.infrastructure.database.entity.PurchaseStatusEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseStatusEntityMapperTest {
    private final PurchaseStatusEntityMapper purchaseStatusEntityMapper = new PurchaseStatusEntityMapperImpl();

    @Test
    public void testMappingPurchaseStatusToPurchaseStatusEntity() {
        var statusId = UUID.randomUUID();
        var statusName = "Test purchase status name";
        var statusDescription = "Test purchase status description";

        var purchaseStatus = new PurchaseStatus(statusId, statusName, statusDescription);
        var expectedPurchaseStatusEntity = new PurchaseStatusEntity(statusId, statusName, statusDescription);
        var actualPurchaseStatusEntity = purchaseStatusEntityMapper.toPurchaseStatusEntity(purchaseStatus);

        assertThat(actualPurchaseStatusEntity)
                .isNotNull()
                .isEqualTo(expectedPurchaseStatusEntity);
    }

    @Test
    public void testMappingPurchaseStatusEntityToPurchaseStatus() {
        var statusId = UUID.randomUUID();
        var statusName = "Test purchase status name";
        var statusDescription = "Test purchase status description";

        var purchaseStatusEntity = new PurchaseStatusEntity(statusId, statusName, statusDescription);
        var expectedPurchaseStatus = new PurchaseStatus(statusId, statusName, statusDescription);
        var actualPurchaseStatus = purchaseStatusEntityMapper.toPurchaseStatus(purchaseStatusEntity);

        assertThat(actualPurchaseStatus)
                .isNotNull()
                .isEqualTo(expectedPurchaseStatus);
    }
}
