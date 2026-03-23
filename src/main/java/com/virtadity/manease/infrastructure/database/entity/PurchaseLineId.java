package com.virtadity.manease.infrastructure.database.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseLineId implements Serializable {
    private UUID purchaseId;
    private UUID productId;
}
