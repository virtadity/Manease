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
public class ApprovedPurchaseStatusHandler {

    private final PurchaseStatusGetByNameOutputBoundary purchaseStatusGetByName;
    private final PurchaseStatusCreateOutputBoundary purchaseStatusCreate;

    @Cacheable("approvedPurchaseStatus")
    public PurchaseStatus get() {
        return purchaseStatusGetByName
                .getOneByName("approved")
                .orElseGet(this::createDefaultApprovedStatus);
    }

    private PurchaseStatus createDefaultApprovedStatus() {
        var id = UUID.randomUUID();
        var name = "approved";
        var description = "The purchase status for approved purchases";
        var approvedPurchaseStatus = new PurchaseStatus(id, name, description);
        return purchaseStatusCreate.createPurchaseStatus(approvedPurchaseStatus);
    }
}
