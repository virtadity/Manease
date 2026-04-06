package com.manease.producer.application.port.out.purchase.status;

import com.manease.producer.domain.entity.purchase.PurchaseStatus;

import java.util.Optional;

public interface PurchaseStatusGetByNameOutputBoundary {
    Optional<PurchaseStatus> getByName(String name);
}
