package com.manease.producer.application.mapper.purchase.status;

import com.manease.producer.application.entity.purchase.status.PurchaseStatusResponse;
import com.manease.producer.application.mapper.purchase.status.PurchaseStatusMapper;
import com.manease.producer.application.mapper.purchase.status.PurchaseStatusMapperImpl;
import com.manease.producer.domain.entity.purchase.PurchaseStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class TestPurchaseStatusMapper {

    private final PurchaseStatusMapper purchaseStatusMapper = new PurchaseStatusMapperImpl();

    @Test
    void testMappingPurchaseStatusToPurchaseStatusResponse() {
        var id = UUID.randomUUID();
        var name = "Test status name";
        var description = "Test status description";
        var purchaseStatus = new PurchaseStatus(id, name, description);
        var expectedPurchaseResponse = new PurchaseStatusResponse(id, name, description);
        var actualPurchaseResponse = purchaseStatusMapper.toPurchaseStatusResponse(purchaseStatus);
        Assertions.assertEquals(expectedPurchaseResponse, actualPurchaseResponse);
    }
}
