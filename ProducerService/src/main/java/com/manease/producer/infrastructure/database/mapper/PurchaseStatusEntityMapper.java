package com.manease.producer.infrastructure.database.mapper;

import com.manease.producer.domain.entity.purchase.PurchaseStatus;
import com.manease.producer.infrastructure.database.entity.PurchaseStatusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseStatusEntityMapper {
    PurchaseStatusEntity toPurchaseStatusEntity(PurchaseStatus purchaseStatus);
    PurchaseStatus toPurchaseStatus(PurchaseStatusEntity purchaseStatusEntity);
    List<PurchaseStatus> toPurchaseStatusList(List<PurchaseStatusEntity> purchaseStatusEntityList);
}
