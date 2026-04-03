package com.manease.producer.application.port.in.purchase.getters;

import com.manease.producer.application.entity.purchase.PurchaseResponse;

import java.util.UUID;

public interface PurchaseGetOneInputBoundary {
    PurchaseResponse execute(UUID id, UUID producerId);
}
