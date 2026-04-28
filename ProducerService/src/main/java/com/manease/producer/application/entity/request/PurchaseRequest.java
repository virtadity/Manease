package com.manease.producer.application.entity.request;

import java.util.UUID;

public record PurchaseRequest(UUID id, UUID producerId) {}
