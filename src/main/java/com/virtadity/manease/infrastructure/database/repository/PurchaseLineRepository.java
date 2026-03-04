package com.virtadity.manease.infrastructure.database.repository;

import com.virtadity.manease.infrastructure.database.entity.PurchaseLineEntity;
import com.virtadity.manease.infrastructure.database.entity.PurchaseLineId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PurchaseLineRepository extends JpaRepository<PurchaseLineEntity, PurchaseLineId> {
    @Query(value = """
            SELECT * FROM purchase_line
            WHERE purchase_id = :purchaseId
            """, nativeQuery = true)
    List<PurchaseLineEntity> getPurchaseLinesOfPurchase(@Param("purchaseId") UUID purchaseId);
}
