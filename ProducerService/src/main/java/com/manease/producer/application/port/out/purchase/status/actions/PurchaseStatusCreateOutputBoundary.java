package com.manease.producer.application.port.out.purchase.status.actions;

import com.manease.producer.domain.entity.PurchaseStatus;

public interface PurchaseStatusCreateOutputBoundary {
    PurchaseStatus createPurchaseStatus(PurchaseStatus purchaseStatus);
}
