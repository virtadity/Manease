package com.manease.producer.application.entity.purchase.status;

import java.util.UUID;

public record PurchaseStatusResponse(UUID id, String name, String description) {}
