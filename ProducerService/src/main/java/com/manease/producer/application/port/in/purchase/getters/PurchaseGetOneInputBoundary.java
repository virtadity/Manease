package com.manease.producer.application.port.in.purchase.getters;

import com.manease.producer.application.entity.response.PurchaseResponse;

import java.util.UUID;

public interface PurchaseGetOneInputBoundary {
    PurchaseResponse execute(UUID purchaseId, UUID producerId);
}
