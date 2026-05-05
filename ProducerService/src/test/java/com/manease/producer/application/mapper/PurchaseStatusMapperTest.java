package com.manease.producer.application.mapper;

import com.manease.producer.application.entity.response.PurchaseStatusResponse;
import com.manease.producer.domain.entity.PurchaseStatus;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseStatusMapperTest {

    private final PurchaseStatusMapper purchaseStatusMapper = new PurchaseStatusMapperImpl();

    @Test
    public void testMappingFromPurchaseStatusToPurchaseStatusResponse() {
        var id = UUID.randomUUID();
        var name = "Test purchase status name";
        var description = "Test purchase status description";

        var purchaseStatus = new PurchaseStatus(id, name, description);
        var expectedPurchaseStatus = new PurchaseStatusResponse(id, name, description);
        var actualPurchaseStatus = purchaseStatusMapper.toPurchaseStatusResponse(purchaseStatus);

        assertThat(actualPurchaseStatus)
                .isNotNull()
                .isEqualTo(expectedPurchaseStatus);
    }
}
