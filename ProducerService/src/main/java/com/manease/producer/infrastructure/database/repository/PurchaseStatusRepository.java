package com.manease.producer.infrastructure.database.repository;

import com.manease.producer.infrastructure.database.entity.PurchaseStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PurchaseStatusRepository extends JpaRepository<PurchaseStatusEntity, UUID> {
    @Query(value = """
            SELECT * FROM PURCHASE_STATUS WHERE purchase_status_name = :name
            LIMIT 1
            """, nativeQuery = true)
    Optional<PurchaseStatusEntity> findByName(@Param("name") String name);
}
