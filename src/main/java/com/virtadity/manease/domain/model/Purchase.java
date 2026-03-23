package com.virtadity.manease.domain.model;


import java.time.LocalDateTime;
import java.util.UUID;

public record Purchase (
     UUID purchaseId,
     String description,
     LocalDateTime creationDate,
     LocalDateTime deliveryDate,
     UUID producerId
) {}
