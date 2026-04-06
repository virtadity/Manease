package com.manease.producer.infrastructure.database.mapper;

import com.manease.producer.domain.entity.purchase.Purchase;
import com.manease.producer.infrastructure.database.entity.PurchaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseEntityMapper {
    PurchaseEntity toPurchaseEntity(Purchase purchase);
    Purchase toPurchase(PurchaseEntity purchaseEntity);
    List<Purchase> toPurchaseList(List<PurchaseEntity> purchaseEntityList);
}
