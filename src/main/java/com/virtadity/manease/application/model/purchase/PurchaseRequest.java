package com.virtadity.manease.application.model.purchase;

import java.time.LocalDateTime;
import java.util.UUID;

public record PurchaseRequest(
        UUID purchaseId,
        String description,
        LocalDateTime creationDate,
        LocalDateTime deliveryDate,
        UUID producerId
) {}
