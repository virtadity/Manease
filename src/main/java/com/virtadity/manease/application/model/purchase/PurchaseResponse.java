package com.virtadity.manease.application.model.purchase;

import java.time.LocalDateTime;
import java.util.UUID;

public record PurchaseResponse(
        UUID purchaseId,
       String description,
       LocalDateTime creationDate,
       LocalDateTime deliveryDate,
       UUID producerId
) {}
