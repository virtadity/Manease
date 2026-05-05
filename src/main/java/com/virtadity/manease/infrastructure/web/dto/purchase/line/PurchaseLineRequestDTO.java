package com.virtadity.manease.infrastructure.web.dto.purchase.line;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Data
@AllArgsConstructor
public class PurchaseLineRequestDTO {
    @JsonProperty("purchase_id")
    private UUID purchaseId;
    @JsonProperty("product_id")
    private UUID productId;
    private BigDecimal price;
    private BigInteger quantity;
}
