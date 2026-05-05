package com.manease.producer.application.mapper;

import com.manease.producer.application.entity.request.PurchaseRequest;
import com.manease.producer.application.entity.response.PurchaseResponse;
import com.manease.producer.domain.entity.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseMapper {
    Purchase toPurchase(PurchaseRequest purchaseRequest, UUID statusId);
    PurchaseResponse toPurchaseResponse(Purchase purchase);
}
