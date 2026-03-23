package com.virtadity.manease.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "PRODUCER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProducerEntity {
    @Id @Column(name = "producer_id", nullable = false)
    private UUID id;

    @Column(name = "producer_name", unique = true, nullable = false)
    private String name;

    public boolean equals(Object object) {
        if (object == null || object.getClass() != this.getClass()) return false;
        var other = (ProducerEntity) object;
        return Objects.equals(this.getId(), other.getId());
    }

    public int hashCode() {
        return id == null ? getClass().hashCode() : Objects.hash(id);
    }
}
