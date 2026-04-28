package com.manease.producer.infrastructure.database.repository;

import com.manease.producer.infrastructure.database.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, UUID> {
    @Query(value = """
            SELECT * FROM PURCHASE WHERE producer_id = :producerId 
            """,
            nativeQuery = true
    )
    List<PurchaseEntity> getAllWithProducer(@Param("producerId") UUID producerId);
}
