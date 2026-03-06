package com.virtadity.manease.application.model.product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
        UUID productId,
        String name,
        BigDecimal weight,
        UUID producerId,
        UUID productTypeId
) {
}
