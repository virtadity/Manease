package com.manease.producer.application.port.in.purchase.actions;

import com.manease.producer.application.entity.purchase.PurchaseResponse;

import java.util.UUID;

public interface PurchaseDeclineInputBoundary {
    PurchaseResponse execute(UUID purchaseId, UUID producerId);
}
