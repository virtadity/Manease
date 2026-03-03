package com.virtadity.manease.infrastructure.database.read_model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface ReportLineReadModel {
    LocalDateTime getDeliveryDate();
    LocalDateTime getCreationDate();
    UUID getPurchaseId();
    BigDecimal getTotalPurchaseWeight();
    BigDecimal getTotalPurchaseCost();
}
