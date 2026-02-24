package com.virtadity.manease.application.model.product;

import java.util.UUID;

public record ProductResponse(
        UUID productId,
        String name,
        Double weight,
        UUID producerId,
        UUID productTypeId
) {
}
