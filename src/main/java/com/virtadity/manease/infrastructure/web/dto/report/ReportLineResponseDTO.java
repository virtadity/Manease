package com.virtadity.manease.infrastructure.web.dto.report;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReportLineResponseDTO {
    UUID purchaseId;
    LocalDateTime deliveryDate;
    LocalDateTime creationDate;
    BigDecimal totalPurchaseWeight;
    BigDecimal totalPurchaseCost;
}
