package com.manease.producer.application.mapper.purchase;

import com.manease.producer.application.entity.purchase.PurchaseRequest;
import com.manease.producer.application.entity.purchase.PurchaseResponse;
import com.manease.producer.application.mapper.purchase.PurchaseMapper;
import com.manease.producer.application.mapper.purchase.PurchaseMapperImpl;
import com.manease.producer.domain.entity.purchase.Purchase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class TestPurchaseMapper {

    private final PurchaseMapper purchaseMapper = new PurchaseMapperImpl();

    @Test
    void testMappingPurchaseRequestToPurchase() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var purchaseStatusId = UUID.randomUUID();
        var purchaseRequest = new PurchaseRequest(id, producerId);
        var expectedPurchase = new Purchase(id, producerId, purchaseStatusId);
        var actualPurchase = purchaseMapper.toPurchase(purchaseRequest, purchaseStatusId);
        Assertions.assertEquals(expectedPurchase, actualPurchase);
    }

    @Test
    void testMappingPurchaseToPurchaseResponse() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var purchaseStatusId = UUID.randomUUID();
        var purchase = new Purchase(id, producerId, purchaseStatusId);
        var expectedPurchaseResponse = new PurchaseResponse(id, producerId, purchaseStatusId);
        var actualPurchaseResponse = purchaseMapper.toPurchaseResponse(purchase);
        Assertions.assertEquals(expectedPurchaseResponse, actualPurchaseResponse);
    }

}
