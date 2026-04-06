package com.manease.producer.application.port.out.purchase.getters;

import com.manease.producer.domain.entity.purchase.Purchase;

import java.util.List;
import java.util.UUID;

public interface PurchaseGetAllWithStatusOutputBoundary {
    List<Purchase> getAllWith(UUID producerId, UUID purchaseStatusId);
}
