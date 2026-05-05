package com.manease.producer.application.port.out.purchase.status.getters;

import com.manease.producer.domain.entity.PurchaseStatus;

import java.util.Optional;

public interface PurchaseStatusGetByNameOutputBoundary {
    Optional<PurchaseStatus> getOneByName(String name);
}
