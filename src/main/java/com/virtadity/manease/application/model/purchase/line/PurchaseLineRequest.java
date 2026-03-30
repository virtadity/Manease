package com.virtadity.manease.application.model.purchase.line;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public record PurchaseLineRequest(
    UUID purchaseId,
    UUID productId,
    BigDecimal price,
    BigInteger quantity
) {}
