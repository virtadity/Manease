package com.virtadity.manease.repository;

import com.virtadity.manease.entity.PurchaseLine;
import com.virtadity.manease.entity.PurchaseLineCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseLineRepository extends JpaRepository<PurchaseLine, PurchaseLineCompositeKey> {
    @Query(value = "SELECT SUM(quantity * price) FROM PURCHASE_LINE WHERE supply_id = ?1", nativeQuery = true)
    Float countTotalPriceOfSupply(Long supplyId);

    @Query(value = "SELECT SUM(weight * quantity) FROM" +
            " PURCHASE_LINE INNER JOIN PRODUCT" +
            " ON PURCHASE_LINE.product_id = PRODUCT.product_id" +
            " WHERE supply_id = ?1", nativeQuery = true)
    Float countTotalWeightOfSupply(Long supplyId);

}
