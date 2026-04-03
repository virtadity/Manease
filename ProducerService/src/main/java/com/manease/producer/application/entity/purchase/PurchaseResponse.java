package com.manease.producer.application.entity.purchase;

import java.util.UUID;

public record PurchaseResponse(UUID id, UUID producerId, UUID purchaseStatusId) {
}
