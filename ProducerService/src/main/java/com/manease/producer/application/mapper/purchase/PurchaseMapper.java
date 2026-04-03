package com.manease.producer.application.mapper.purchase;

import com.manease.producer.application.entity.purchase.PurchaseRequest;
import com.manease.producer.application.entity.purchase.PurchaseResponse;
import com.manease.producer.domain.entity.purchase.Purchase;
import com.manease.producer.domain.entity.purchase.PurchaseStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseMapper {
    @Mapping(source = "purchaseRequest.id", target = "id")
    @Mapping(source = "purchaseRequest.producerId", target = "producerId")
    @Mapping(source = "purchaseStatusId", target = "purchaseStatusId")
    Purchase toPurchase(PurchaseRequest purchaseRequest, UUID purchaseStatusId);

    PurchaseResponse toPurchaseResponse(Purchase purchase);

    List<PurchaseResponse> toPurchaseResponseList(List<Purchase> purchaseList);
}
