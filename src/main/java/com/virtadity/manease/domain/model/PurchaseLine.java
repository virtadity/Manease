package com.virtadity.manease.domain.model;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;


public record PurchaseLine (
    UUID purchaseId,
    UUID productId,
    BigDecimal price,
    BigInteger quantity
) {}
