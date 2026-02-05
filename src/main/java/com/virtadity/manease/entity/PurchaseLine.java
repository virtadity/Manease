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

    private Float price;
    private Integer quantity;

    public PurchaseLine() {}

    public PurchaseLine(
            PurchaseLineCompositeKey id,
            Supply supply,
            Product product,
            Float price,
            Integer quantity
            ) {

        this.id = id;
        this.supply = supply;
        this.product = product;
        this.price = price;
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

    public Float getPrice() {
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

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PurchaseLine that)) return false;
        var areSupplyEqual = Objects.equals(getSupply(), that.getSupply());
        var areProductEqual = Objects.equals(getProduct(), that.getProduct());
        var arePriceEqual = Objects.equals(getPrice(), that.getPrice());
        var areQuantityEqual = Objects.equals(getQuantity(), that.getQuantity());

        return areSupplyEqual && areProductEqual && arePriceEqual && areQuantityEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSupply(), getProduct(), getPrice(), getQuantity());
    }
}
