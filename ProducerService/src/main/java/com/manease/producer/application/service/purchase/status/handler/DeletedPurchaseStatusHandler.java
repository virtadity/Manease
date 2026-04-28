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
public class DeletedPurchaseStatusHandler {

    private final PurchaseStatusGetByNameOutputBoundary purchaseStatusGetByName;
    private final PurchaseStatusCreateOutputBoundary purchaseStatusCreate;

    @Cacheable("deletedPurchaseStatus")
    public PurchaseStatus get() {
        return purchaseStatusGetByName
                .getOneByName("deleted")
                .orElseGet(this::createDefaultDeletedStatus);
    }

    private PurchaseStatus createDefaultDeletedStatus() {
        var id = UUID.randomUUID();
        var name = "deleted";
        var description = "The purchase status for deleted purchases";
        var deletedPurchaseStatus = new PurchaseStatus(id, name, description);
        return purchaseStatusCreate.createPurchaseStatus(deletedPurchaseStatus);
    }
}
