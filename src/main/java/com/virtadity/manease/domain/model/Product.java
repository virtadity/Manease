package com.virtadity.manease.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record Product (
    UUID productId,
    String name,
    BigDecimal weight,
    UUID producerId,
    UUID productTypeId
) {}
