package com.virtadity.manease.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "PRODUCT_TYPE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeEntity {
    @Id @Column(name = "product_type_id", nullable = false)
    private UUID id;

    @Column(name = "product_type_name", nullable = false, unique = true)
    private String name;

    public boolean equals(Object object) {
        if (object == null || object.getClass() != this.getClass()) return false;
        var other = (ProductTypeEntity) object;
        return Objects.equals(this.getId(), other.getId());
    }

    public int hashCode() {
        return id == null ? getClass().hashCode() : Objects.hash(id);
    }
}
