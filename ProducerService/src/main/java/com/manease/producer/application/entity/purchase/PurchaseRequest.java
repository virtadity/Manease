package com.manease.producer.application.entity.purchase;

import java.util.UUID;

public record PurchaseRequest(UUID id, UUID producerId) {
}
