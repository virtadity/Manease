package com.manease.producer.application.port.out.purchase;

import com.manease.producer.domain.entity.purchase.Purchase;

import java.util.UUID;

public interface PurchaseSetStatusOutputBoundary {
    Purchase setStatus(UUID purchaseId, UUID purchaseStatusId);
}
