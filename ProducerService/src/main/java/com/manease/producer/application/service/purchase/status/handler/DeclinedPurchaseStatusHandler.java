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
public class DeclinedPurchaseStatusHandler {

    private final PurchaseStatusGetByNameOutputBoundary purchaseStatusGetByName;
    private final PurchaseStatusCreateOutputBoundary purchaseStatusCreateOutputBoundary;

    @Cacheable("declinedPurchaseStatus")
    public PurchaseStatus get() {
        return purchaseStatusGetByName
                .getOneByName("declined")
                .orElseGet(this::createDefaultDeclinedStatus);
    }

    private PurchaseStatus createDefaultDeclinedStatus() {
        var id = UUID.randomUUID();
        var name = "declined";
        var description = "The purchase status for purchases declined by manufacturer";
        var declinedPurchaseStatus = new PurchaseStatus(id, name, description);
        return purchaseStatusCreateOutputBoundary.createPurchaseStatus(declinedPurchaseStatus);
    }
}
