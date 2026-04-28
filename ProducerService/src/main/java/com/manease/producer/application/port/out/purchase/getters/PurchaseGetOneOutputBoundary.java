package com.manease.producer.application.port.out.purchase.getters;

import com.manease.producer.domain.entity.Purchase;

import java.util.Optional;
import java.util.UUID;

public interface PurchaseGetOneOutputBoundary {
    Optional<Purchase> getOneById(UUID id);
}
