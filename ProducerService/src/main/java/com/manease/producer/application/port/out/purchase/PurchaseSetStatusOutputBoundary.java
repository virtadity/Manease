package com.manease.producer.application.port.out.purchase;

import com.manease.producer.domain.entity.purchase.Purchase;

import java.util.UUID;

public interface PurchaseSetStatusOutputBoundary {
    Purchase execute(UUID purchaseId, UUID purchaseStatusId);
}
