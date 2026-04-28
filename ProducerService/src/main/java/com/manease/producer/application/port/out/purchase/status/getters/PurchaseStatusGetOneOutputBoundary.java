package com.manease.producer.application.port.out.purchase.status.getters;

import com.manease.producer.domain.entity.PurchaseStatus;

import java.util.Optional;
import java.util.UUID;

public interface PurchaseStatusGetOneOutputBoundary {
    Optional<PurchaseStatus> getOneById(UUID id);
}
