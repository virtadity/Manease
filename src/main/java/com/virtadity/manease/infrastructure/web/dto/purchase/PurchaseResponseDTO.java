package com.virtadity.manease.infrastructure.web.dto.purchase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class PurchaseResponseDTO {
    private UUID id;
    private String description;
    @JsonProperty("creation_date")
    private LocalDateTime creationDate;
    @JsonProperty("delivery_date")
    private LocalDateTime deliveryDate;
    @JsonProperty("producer_id")
    private UUID producerId;
}
