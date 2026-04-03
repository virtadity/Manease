package com.manease.producer.application.port.in.purchase.getters;

import com.manease.producer.application.entity.purchase.PurchaseResponse;

import java.util.List;
import java.util.UUID;

public interface PurchaseGetAllCreatedInputBoundary {
    List<PurchaseResponse> execute(UUID producerId);
}
