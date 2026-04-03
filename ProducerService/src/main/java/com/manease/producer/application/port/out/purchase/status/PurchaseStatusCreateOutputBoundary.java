package com.manease.producer.application.port.out.purchase.status;

import com.manease.producer.domain.entity.purchase.PurchaseStatus;

public interface PurchaseStatusCreateOutputBoundary {
    PurchaseStatus create(PurchaseStatus purchaseStatus);
}
