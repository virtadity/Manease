package com.virtadity.manease.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Float weight;

    @ManyToOne
    @JoinColumn(name="product_type_id", nullable = false)
    private ProductType productType;

    @ManyToMany(mappedBy = "products")
    private Set<Manufacturer> manufacturers;

    @OneToMany(mappedBy = "product")
    private Set<PurchaseLine> purchaseLines;

    public Product() {}

    public Product(String name, Float weight, ProductType productType) {
        this.name = name;
        this.manufacturers = new HashSet<>();
        this.purchaseLines = new HashSet<>();
        this.weight = weight;
        this.productType = productType;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getWeight() {
        return weight;
    }

    public ProductType getProductType() {
        return productType;
    }

    public Set<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public Set<PurchaseLine> getPurchaseLines() {
        return purchaseLines;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public void setManufacturers(Set<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public void setPurchaseLines(Set<PurchaseLine> purchaseLines) {
        this.purchaseLines = purchaseLines;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
