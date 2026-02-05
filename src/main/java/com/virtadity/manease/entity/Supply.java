package com.virtadity.manease.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date deliveryDate;

    @OneToMany(mappedBy = "supply")
    private Set<PurchaseLine> purchaseLines;

    public Supply() {}

    public Supply(Manufacturer manufacturer, String description, Date deliveryDate) {
        this.manufacturer = manufacturer;
        this.description = description;
        this.deliveryDate = deliveryDate;
        this.purchaseLines = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public Manufacturer getSupplier() {
        return manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public Set<PurchaseLine> getPurchaseLines() {
        return purchaseLines;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSupplier(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setPurchaseLines(Set<PurchaseLine> purchaseLines) {
        this.purchaseLines = purchaseLines;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Supply supply)) return false;
        return Objects.equals(getId(), supply.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
