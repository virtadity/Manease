package com.virtadity.manease.infrastructure.database.mapper;

import com.virtadity.manease.domain.model.Purchase;
import com.virtadity.manease.infrastructure.database.entity.ProducerEntity;
import com.virtadity.manease.infrastructure.database.entity.PurchaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseEntityMapper {
    @Mapping(source = "id", target = "purchaseId")
    @Mapping(target = "producerId", expression = "java(purchaseEntity.getProducer().getId())")
    Purchase toPurchase(PurchaseEntity purchaseEntity);

    @Mapping(target = "id", expression = "java(purchase.purchaseId())")
    @Mapping(source = "producerEntity", target = "producer")
    PurchaseEntity toPurchaseEntity(Purchase purchase, ProducerEntity producerEntity);

    List<Purchase> toPurchaseList(List<PurchaseEntity> purchaseEntityList);

    @Mapping(target = "id", expression = "java(purchase.purchaseId())")
    @Mapping(source = "producerEntity", target = "producer")
    void updateFromPurchase(
            @MappingTarget PurchaseEntity purchaseEntity,
            Purchase purchase,
            ProducerEntity producerEntity
    );
}
