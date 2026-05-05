package com.manease.producer.application.mapper;

import com.manease.producer.application.entity.response.PurchaseStatusResponse;
import com.manease.producer.domain.entity.PurchaseStatus;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseStatusMapper {
    PurchaseStatusResponse toPurchaseStatusResponse(PurchaseStatus purchaseStatus);
}
