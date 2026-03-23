package com.virtadity.manease.infrastructure.web.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductRequestDTO {
    private UUID id;
    private String name;
    private BigDecimal weight;
    @JsonProperty("producer_id") private UUID producerId;
    @JsonProperty("product_type_id") private UUID productTypeId;
}
