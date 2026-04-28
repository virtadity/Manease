package com.manease.producer.application.port.in.purchase.actions;

import com.manease.producer.application.entity.response.PurchaseResponse;

import java.util.UUID;

public interface PurchaseDeleteInputBoundary {
    PurchaseResponse execute(UUID purchaseId, UUID producerId);
}
