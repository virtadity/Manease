package com.manease.producer.application.port.out.purchase.getters;

import com.manease.producer.domain.entity.Purchase;

import java.util.List;
import java.util.UUID;

public interface PurchaseGetAllOutputBoundary {
    List<Purchase> getAll(UUID producerId);
}
