package com.virtadity.manease.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PurchaseLineCompositeKey implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "supply_id")
    private Long supplyId;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PurchaseLineCompositeKey that)) return false;
        return Objects.equals(productId, that.productId) && Objects.equals(supplyId, that.supplyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, supplyId);
    }

    @Override
    public String toString() {
        return "PurchaseLineCompositeKey{" +
                "productId=" + productId +
                ", supplyId=" + supplyId +
                '}';
    }
}
