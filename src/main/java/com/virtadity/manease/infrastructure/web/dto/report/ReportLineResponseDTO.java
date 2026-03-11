package com.virtadity.manease.infrastructure.web.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ReportLineResponseDTO {
    UUID purchaseId;
    LocalDateTime deliveryDate;
    LocalDateTime creationDate;
    BigDecimal totalPurchaseWeight;
    BigDecimal totalPurchaseCost;
}
