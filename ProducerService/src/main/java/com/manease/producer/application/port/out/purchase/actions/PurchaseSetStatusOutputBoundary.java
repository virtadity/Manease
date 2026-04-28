package com.manease.producer.application.port.out.purchase.actions;

import com.manease.producer.domain.entity.Purchase;

import java.util.UUID;

public interface PurchaseSetStatusOutputBoundary {
    Purchase setStatusToPurchase(UUID purchaseId, UUID purchaseStatusId);
}
