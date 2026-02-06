package com.virtadity.manease.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PurchaseLineCompositeKey implements Serializable {

    @Column(name = "supply_id")
    private Long supplyId;

    @Column(name = "product_id")
    private Long productId;

    public PurchaseLineCompositeKey() {}

    public PurchaseLineCompositeKey(Long supplyId, Long productId) {
        this.supplyId = supplyId;
        this.productId = productId;
    }

    public Long getSupplyId() {
        return supplyId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setSupplyId(Long supplyId) {
        this.supplyId = supplyId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

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
