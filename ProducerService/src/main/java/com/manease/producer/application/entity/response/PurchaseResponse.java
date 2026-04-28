package com.manease.producer.application.entity.response;

import java.util.UUID;

public record PurchaseResponse(UUID id, UUID producerId, UUID statusId) {}
