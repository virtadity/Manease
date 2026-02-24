package com.virtadity.manease.application.model.product;

import java.util.UUID;

public record ProductRequest(
        UUID productId,
        String name,
        Double weight,
        UUID producerId,
        UUID productTypeId
) {
}
