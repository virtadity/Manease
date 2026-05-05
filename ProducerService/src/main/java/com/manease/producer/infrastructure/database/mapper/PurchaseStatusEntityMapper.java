package com.manease.producer.infrastructure.database.mapper;

import com.manease.producer.domain.entity.PurchaseStatus;
import com.manease.producer.infrastructure.database.entity.PurchaseStatusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseStatusEntityMapper {
    PurchaseStatusEntity toPurchaseStatusEntity(PurchaseStatus purchaseStatus);
    PurchaseStatus toPurchaseStatus(PurchaseStatusEntity purchaseStatusEntity);
}
