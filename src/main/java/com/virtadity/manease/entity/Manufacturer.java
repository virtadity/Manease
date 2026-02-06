package com.virtadity.manease.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    private Set<Product> products;

    @OneToMany(mappedBy = "manufacturer")
    private Set<Supply> supplies;

    public Manufacturer() {}

    public Manufacturer(String name) {
        this.name = name;
        this.products = new HashSet<>();
        this.supplies = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Set<Supply> getSupplies() {
        return supplies;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void setSupplies(Set<Supply> supplies) {
        this.supplies = supplies;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Manufacturer manufacturer)) return false;
        return Objects.equals(getId(), manufacturer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
