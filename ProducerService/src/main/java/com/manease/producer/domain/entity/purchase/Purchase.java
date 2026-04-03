package com.manease.producer.domain.entity.purchase;

import java.util.UUID;

public record Purchase(UUID id, UUID producerId, UUID purchaseStatusId) {
}
