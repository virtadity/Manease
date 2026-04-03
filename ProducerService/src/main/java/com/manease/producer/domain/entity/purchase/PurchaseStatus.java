package com.manease.producer.domain.entity.purchase;

import java.util.UUID;

public record PurchaseStatus(UUID id, String name, String description) {
    public PurchaseStatus(String name, String description) {
        this(UUID.randomUUID(), name, description);
    }
}
