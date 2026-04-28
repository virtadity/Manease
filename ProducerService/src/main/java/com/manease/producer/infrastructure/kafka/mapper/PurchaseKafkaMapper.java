package com.manease.producer.infrastructure.kafka.mapper;

import com.manease.producer.application.entity.request.PurchaseRequest;
import com.manease.producer.infrastructure.kafka.dto.PurchaseCreationKafkaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseKafkaMapper {
    PurchaseRequest toPurchaseRequest(PurchaseCreationKafkaDTO purchaseDTO);
}
