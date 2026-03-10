package com.virtadity.manease.infrastructure.web.dto.product;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductRequestDTO {
    private UUID id;
    private String name;
    private BigDecimal weight;
    private UUID producerId;
    private UUID productTypeId;
}
