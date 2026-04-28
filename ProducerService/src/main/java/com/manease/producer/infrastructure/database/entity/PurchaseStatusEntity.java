package com.manease.producer.infrastructure.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "PURCHASE_STATUS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseStatusEntity {
    @Id @Column(name = "purchase_status_id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "purchase_status_name", nullable = false, unique = true)
    private String name;

    @Column(name = "purchase_status_description", nullable = false)
    private String description;

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        var that = (PurchaseStatusEntity) other;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
