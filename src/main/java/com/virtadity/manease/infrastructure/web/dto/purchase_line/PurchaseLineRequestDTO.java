package com.virtadity.manease.infrastructure.web.dto.purchase_line;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Data
public class PurchaseLineRequestDTO {
    private UUID purchaseId;
    private UUID productId;
    private BigDecimal price;
    private BigInteger quantity;
}
