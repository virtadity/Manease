package com.virtadity.manease.application.mapper;

import com.virtadity.manease.application.model.purchase.PurchaseRequest;
import com.virtadity.manease.application.model.purchase.PurchaseResponse;
import com.virtadity.manease.domain.model.Purchase;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestPurchaseMapper {
    private final PurchaseMapper purchaseMapper = new PurchaseMapperImpl();

    @Test
    void testPurchaseRequestToPurchase() {
        var purchaseId = UUID.randomUUID();
        var description = "Тестовая закупка";
        var creationDate = LocalDateTime.now();
        var deliveryDate = LocalDateTime.now();
        var producerId = UUID.randomUUID();

        var purchaseRequest = new PurchaseRequest(purchaseId, description, creationDate, deliveryDate, producerId);
        var expectedPurchase = new Purchase(purchaseId, description, creationDate, deliveryDate, producerId);
        var actualPurchase = purchaseMapper.toPurchase(purchaseRequest);

        assertThat(actualPurchase).isNotNull().isEqualTo(expectedPurchase);
    }

    @Test
    void testPurchaseRequestToPurchaseWithNullId() {
        var description = "Тестовая закупка";
        var creationDate = LocalDateTime.now();
        var deliveryDate = LocalDateTime.now();
        var producerId = UUID.randomUUID();

        var purchaseRequest = new PurchaseRequest(null, description, creationDate, deliveryDate, producerId);
        var actualPurchase = purchaseMapper.toPurchase(purchaseRequest);

        assertThat(actualPurchase).isNotNull();
        assertThat(actualPurchase.purchaseId()).isNotNull();
        assertThat(actualPurchase.description()).isNotNull().isEqualTo(description);
        assertThat(actualPurchase.creationDate()).isNotNull().isEqualTo(creationDate);
        assertThat(actualPurchase.deliveryDate()).isNotNull().isEqualTo(deliveryDate);
        assertThat(actualPurchase.producerId()).isNotNull().isEqualTo(producerId);
    }

    @Test
    void testPurchaseToPurchaseResponse() {
        var purchaseId = UUID.randomUUID();
        var description = "Тестовая закупка";
        var creationDate = LocalDateTime.now();
        var deliveryDate = LocalDateTime.now();
        var producerId = UUID.randomUUID();

        var purchase = new Purchase(
                purchaseId,
                description,
                creationDate,
                deliveryDate,
                producerId
        );

        var expectedPurchaseResponse = new PurchaseResponse(
                purchaseId,
                description,
                creationDate,
                deliveryDate,
                producerId
        );

        var actualPurchaseResponse = purchaseMapper.toPurchaseResponse(purchase);

        assertThat(actualPurchaseResponse).isNotNull().isEqualTo(expectedPurchaseResponse);
    }
}
