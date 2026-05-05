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
public class CreatedPurchaseStatusHandler {

    private final PurchaseStatusGetByNameOutputBoundary purchaseStatusGetByName;
    private final PurchaseStatusCreateOutputBoundary purchaseStatusCreateOutputBoundary;

    @Cacheable("createdPurchaseStatus")
    public PurchaseStatus get() {
        return purchaseStatusGetByName
                .getOneByName("created")
                .orElseGet(this::createDefaultCreatedStatus);
    }

    private PurchaseStatus createDefaultCreatedStatus() {
        var id = UUID.randomUUID();
        var name = "created";
        var description = "The purchase status for new purchases";
        var createdPurchaseStatus = new PurchaseStatus(id, name, description);
        return purchaseStatusCreateOutputBoundary.createPurchaseStatus(createdPurchaseStatus);
    }
}
