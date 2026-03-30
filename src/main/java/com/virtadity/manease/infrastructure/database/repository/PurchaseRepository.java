package com.virtadity.manease.infrastructure.database.repository;

import com.virtadity.manease.infrastructure.database.entity.PurchaseEntity;
import com.virtadity.manease.infrastructure.database.readmodel.ReportLineReadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, UUID> {

    @Query(value = """
            SELECT
                purchase.purchase_id AS purchaseId,
                purchase_delivery_date AS deliveryDate,
                purchase_creation_date AS creationDate,
                SUM(purchase_line_price * purchase_line_quantity) AS totalPurchaseCost,
                SUM(purchase_line_quantity * product_weight) AS totalPurchaseWeight
            FROM
                purchase
            INNER JOIN
                purchase_line USING(purchase_id)
            INNER JOIN
                product USING(product_id)
            WHERE purchase_delivery_date BETWEEN :afterDate AND :beforeDate
            GROUP BY purchase_id;
            """, nativeQuery = true)
    List<ReportLineReadModel> getReportLineBetweenDates(
            @Param("afterDate") LocalDateTime fromDate,
            @Param("beforeDate") LocalDateTime toDate
    );
}
