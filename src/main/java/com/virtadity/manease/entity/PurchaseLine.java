package com.virtadity.manease.entity;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class PurchaseLine {

    @EmbeddedId
    private PurchaseLineCompositeKey id;

    @ManyToOne
    @MapsId("supplyId")
    @JoinColumn(name="supply_id")
    private Supply supply;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name="product_id")
    private Product product;

    private Integer price;
    private Integer quantity;

    public PurchaseLine() {}

    public PurchaseLine(
            PurchaseLineCompositeKey id,
            Supply supply,
            Product product,
            Integer cost,
            Integer quantity
            ) {

        this.id = id;
        this.supply = supply;
        this.product = product;
        this.price = cost;
        this.quantity = quantity;
    }

    public PurchaseLineCompositeKey getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Supply getSupply() {
        return supply;
    }

    public Integer getCost() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setId(PurchaseLineCompositeKey id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSupply(Supply supply) {
        this.supply = supply;
    }

    public void setCost(Integer cost) {
        this.price = cost;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PurchaseLine that)) return false;
        var areSupplyEqual = Objects.equals(getSupply(), that.getSupply());
        var areProductEqual = Objects.equals(getProduct(), that.getProduct());
        var areCostEqual = Objects.equals(getCost(), that.getCost());
        var areQuantityEqual = Objects.equals(getQuantity(), that.getQuantity());

        return areSupplyEqual && areProductEqual && areCostEqual && areQuantityEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSupply(), getProduct(), getCost(), getQuantity());
    }
}
