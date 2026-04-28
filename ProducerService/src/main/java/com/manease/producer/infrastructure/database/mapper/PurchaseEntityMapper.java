package com.manease.producer.infrastructure.database.mapper;

import com.manease.producer.domain.entity.Purchase;
import com.manease.producer.infrastructure.database.entity.PurchaseEntity;
import com.manease.producer.infrastructure.database.entity.PurchaseStatusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseEntityMapper {
    @Mapping(target = "id", expression = "java(purchase.id())")
    PurchaseEntity toPurchaseEntity(Purchase purchase, PurchaseStatusEntity purchaseStatus);

    @Mapping(target = "statusId", expression = "java(purchaseEntity.getPurchaseStatus().getId())")
    Purchase toPurchase(PurchaseEntity purchaseEntity);
}
