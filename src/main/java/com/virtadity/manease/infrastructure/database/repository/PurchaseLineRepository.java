package com.virtadity.manease.infrastructure.database.repository;

import com.virtadity.manease.infrastructure.database.entity.PurchaseLineEntity;
import com.virtadity.manease.infrastructure.database.entity.PurchaseLineId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseLineRepository extends JpaRepository<PurchaseLineEntity, PurchaseLineId> {
}
