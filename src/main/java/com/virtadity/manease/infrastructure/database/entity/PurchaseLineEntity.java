package com.virtadity.manease.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PURCHASE_LINE")
public class PurchaseLineEntity {

    @EmbeddedId
    private PurchaseLineId id;

    @MapsId("purchaseId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    private PurchaseEntity purchase;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "purchase_line_price", nullable = false)
    private BigDecimal price;
    @Column(name = "purchase_line_quantity", nullable = false)
    private BigInteger quantity;

    public boolean equals(Object object) {
        if (object == null || object.getClass() != this.getClass()) return false;
        var other = (PurchaseLineEntity) object;
        return Objects.equals(this.getId(), other.getId());
    }

    public int hashCode() {
        return id == null ? getClass().hashCode() : Objects.hash(id);
    }
}
