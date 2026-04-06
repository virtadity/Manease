package com.manease.producer.infrastructure.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Entity(name = "PURCHASE")
@Data
@AllArgsConstructor
public class PurchaseEntity {

    @Id @Column(unique = true, nullable = false)
    private UUID purchaseId;

    @Column(nullable = false)
    private UUID producerId;

    @ManyToOne
    @JoinColumn(name = "purchase_status_id")
    private PurchaseStatusEntity purchaseStatusEntity;

}
