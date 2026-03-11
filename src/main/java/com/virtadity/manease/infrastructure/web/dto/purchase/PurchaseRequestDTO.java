package com.virtadity.manease.infrastructure.web.dto.purchase;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class PurchaseRequestDTO {
    private UUID id;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime deliveryDate;
    private UUID producerId;
}
