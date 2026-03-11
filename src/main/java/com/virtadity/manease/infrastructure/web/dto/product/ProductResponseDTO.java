package com.virtadity.manease.infrastructure.web.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductResponseDTO {
    private UUID id;
    private String name;
    private BigDecimal weight;
    private UUID producerId;
    private UUID productTypeId;
}
