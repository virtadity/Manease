package com.manease.producer.infrastructure.database.repository;

import com.manease.producer.infrastructure.database.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, UUID> {
    @Query(value = "SELECT * FROM PURCHASE WHERE PRODUCER_ID = :producer_id", nativeQuery = true)
    List<PurchaseEntity> findAllWithProducer(@Param("producer_id") UUID producerId);

    @Query(value = """
            SELECT * FROM PURCHASE WHERE PRODUCER_ID = :producer_id AND PURCHASE_STATUS_ID = :purchase_status_id
            """,
            nativeQuery = true
    )
    List<PurchaseEntity> findAllWithProducerAndStatus(
            @Param("producer_id") UUID producerId,
            @Param("purchase_status_id") UUID purchaseStatusId
    );
}
