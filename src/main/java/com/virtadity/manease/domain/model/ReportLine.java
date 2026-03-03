package com.virtadity.manease.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ReportLine(
        LocalDateTime deliveryDate,
        LocalDateTime creationDate,
        UUID supplyId,
        BigDecimal totalPurchaseWeight,
        BigDecimal totalPurchaseCost
) {
}
