package com.virtadity.manease.infrastructure.database.mapper;

import com.virtadity.manease.domain.model.PurchaseLine;
import com.virtadity.manease.infrastructure.database.entity.ProductEntity;
import com.virtadity.manease.infrastructure.database.entity.PurchaseEntity;
import com.virtadity.manease.infrastructure.database.entity.PurchaseLineEntity;
import com.virtadity.manease.infrastructure.database.entity.PurchaseLineId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseLineEntityMapper {
    @Mapping(target = "purchaseId", expression = "java(purchaseLineEntity.getId().getPurchaseId())")
    @Mapping(target = "productId", expression = "java(purchaseLineEntity.getId().getProductId())")
    PurchaseLine toPurchaseLine(PurchaseLineEntity purchaseLineEntity);

    @Mapping(source = "purchaseLineId", target = "id")
    @Mapping(source = "purchaseEntity", target = "purchase")
    @Mapping(source = "productEntity", target = "product")
    PurchaseLineEntity toPurchaseLineEntity(
            PurchaseLine purchaseLine,
            PurchaseLineId purchaseLineId,
            PurchaseEntity purchaseEntity,
            ProductEntity productEntity
    );

    List<PurchaseLine> toPurchaseLineList(List<PurchaseLineEntity> purchaseLineEntityList);

    @Mapping(target = "id", expression = "java(new PurchaseLineId(purchaseLine.purchaseId(), purchaseLine.productId()))")
    @Mapping(source = "purchaseEntity", target = "purchase")
    @Mapping(source = "productEntity", target = "product")
    void updateFromPurchaseLine(
            @MappingTarget PurchaseLineEntity purchaseLineEntity,
            PurchaseLine purchaseLine,
            PurchaseEntity purchaseEntity,
            ProductEntity productEntity
            );
}
