package com.manease.producer.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "PURCHASE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseEntity {
    @Id
    @Column(name = "purchase_id", unique = true, nullable = false)
    private UUID id;

    @Column(unique = true, nullable = false)
    private UUID producerId;

    @ManyToOne
    @JoinColumn(name = "purchase_status_id", referencedColumnName = "purchase_status_id")
    private PurchaseStatusEntity purchaseStatus;

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        var that = (PurchaseEntity) other;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
