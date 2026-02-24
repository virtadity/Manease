package com.virtadity.manease.domain.model;

import java.util.UUID;

public record Product (
    UUID productId,
    String name,
    Double weight,
    UUID producerId,
    UUID productTypeId
) {}
