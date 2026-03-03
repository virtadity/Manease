package com.virtadity.manease.application.model.report_line;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ReportLineResponse(
        LocalDateTime deliveryDate,
        LocalDateTime creationDate,
        UUID supplyId,
        BigDecimal totalPurchaseWeight,
        BigDecimal totalPurchaseCost
) {
}
