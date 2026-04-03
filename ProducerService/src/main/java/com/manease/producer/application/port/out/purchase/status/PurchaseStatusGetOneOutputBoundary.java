package com.manease.producer.application.port.out.purchase.status;

import com.manease.producer.domain.entity.purchase.PurchaseStatus;

import java.util.Optional;
import java.util.UUID;

public interface PurchaseStatusGetOneOutputBoundary {
    Optional<PurchaseStatus> execute(UUID id);
}
