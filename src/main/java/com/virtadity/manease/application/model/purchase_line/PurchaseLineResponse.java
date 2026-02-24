package com.virtadity.manease.application.model.purchase_line;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public record PurchaseLineResponse(
    UUID purchaseId,
    UUID productId,
    BigDecimal price,
    BigInteger quantity
) {}
