package com.virtadity.manease.infrastructure.kafka.mapper;

import com.virtadity.manease.domain.model.Purchase;
import com.virtadity.manease.infrastructure.kafka.dto.PurchaseCreationKafkaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseKafkaMapper {
    @Mapping(target = "id", source = "producerId")
    PurchaseCreationKafkaDTO toPurchaseCreationKafkaDTO(Purchase purchase);
}
