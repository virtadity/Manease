package com.manease.producer.infrastructure.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Entity(name = "PURCHASE_STATUS")
@Data
@AllArgsConstructor
public class PurchaseStatusEntity {
    @Id @Column(name = "purchase_status_id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "purchase_status_name", unique = true, nullable = false)
    private String name;

    @Column(name = "purchase_status_description", unique = true, nullable = false)
    private String description;
}
