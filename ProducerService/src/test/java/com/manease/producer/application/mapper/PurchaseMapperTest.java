package com.manease.producer.application.mapper;

import com.manease.producer.application.entity.request.PurchaseRequest;
import com.manease.producer.application.entity.response.PurchaseResponse;
import com.manease.producer.domain.entity.Purchase;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseMapperTest {

    private final PurchaseMapper purchaseMapper = new PurchaseMapperImpl();

    @Test
    public void testMappingFromPurchaseRequestToPurchase() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var statusId = UUID.randomUUID();

        var purchaseRequest = new PurchaseRequest(id, producerId);
        var expectedPurchase = new Purchase(id, producerId, statusId);
        var actualPurchase = purchaseMapper.toPurchase(purchaseRequest, statusId);

        assertThat(actualPurchase)
                .isNotNull()
                .isEqualTo(expectedPurchase);
    }

    @Test
    public void testMappingFromPurchaseToPurchaseResponse() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var statusId = UUID.randomUUID();

        var purchase = new Purchase(id, producerId, statusId);
        var expectedPurchaseResponse = new PurchaseResponse(id, producerId, statusId);
        var actualPurchaseResponse = purchaseMapper.toPurchaseResponse(purchase);

        assertThat(actualPurchaseResponse)
                .isNotNull()
                .isEqualTo(expectedPurchaseResponse);
    }
}
