package com.virtadity.manease.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PURCHASE")
public class PurchaseEntity {
    @Id @Column(name = "purchase_id", nullable = false)
    private UUID id;

    @Column(name = "purchase_description", nullable = false)
    private String description;

    @Column(name = "purchase_creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "purchase_delivery_date", nullable = false)
    private LocalDateTime deliveryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id")
    private ProducerEntity producer;

    public boolean equals(Object object) {
        if (object == null || object.getClass() != this.getClass()) return false;
        var other = (PurchaseEntity) object;
        return Objects.equals(this.getId(), other.getId());
    }

    public int hashCode() {
        return id == null ? getClass().hashCode() : Objects.hash(id);
    }
}
