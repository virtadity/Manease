package com.virtadity.manease.repository;

import com.virtadity.manease.entity.PurchaseLine;
import com.virtadity.manease.entity.PurchaseLineCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseLineRepository extends JpaRepository<PurchaseLine, PurchaseLineCompositeKey> {
}
