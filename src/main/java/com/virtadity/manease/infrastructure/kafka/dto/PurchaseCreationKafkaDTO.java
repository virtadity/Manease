package com.virtadity.manease.infrastructure.kafka.dto;

import java.util.UUID;

public record PurchaseCreationKafkaDTO(UUID id, UUID producerId) {}
