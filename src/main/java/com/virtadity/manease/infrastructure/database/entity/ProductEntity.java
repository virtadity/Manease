package com.virtadity.manease.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PRODUCT")
public class ProductEntity {
    @Id @Column(name = "product_id", nullable = false)
    private UUID id;

    @Column(name = "product_name", nullable = false, unique = true)
    private String name;

    @Column(name = "product_weight", nullable = false)
    private BigDecimal weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id")
    private ProducerEntity producer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id")
    private ProductTypeEntity productType;

    public boolean equals(Object object) {
        if (object == null || object.getClass() != this.getClass()) return false;
        var other = (ProductEntity) object;
        return Objects.equals(this.getId(), other.getId());
    }

    public int hashCode() {
        return id == null ? getClass().hashCode() : Objects.hash(id);
    }
}
