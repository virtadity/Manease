package com.manease.producer.application.service.purchase.status.handler;

import com.manease.producer.application.port.out.purchase.status.actions.PurchaseStatusCreateOutputBoundary;
import com.manease.producer.application.port.out.purchase.status.getters.PurchaseStatusGetByNameOutputBoundary;
import com.manease.producer.domain.entity.PurchaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeliveredPurchaseStatusHandler {

    private final PurchaseStatusGetByNameOutputBoundary purchaseStatusGetByName;
    private final PurchaseStatusCreateOutputBoundary purchaseStatusCreateOutputBoundary;

    @Cacheable("deliveredPurchaseStatus")
    public PurchaseStatus get() {
        return purchaseStatusGetByName
                .getOneByName("delivered")
                .orElseGet(this::createDefaultDeliveredStatus);
    }

    private PurchaseStatus createDefaultDeliveredStatus() {
        var id = UUID.randomUUID();
        var name = "delivered";
        var description = "The purchase status for deleted purchases";
        var deliveredPurchaseStatus = new PurchaseStatus(id, name, description);
        return purchaseStatusCreateOutputBoundary.createPurchaseStatus(deliveredPurchaseStatus);
    }
}
