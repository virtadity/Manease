package com.virtadity.manease.infrastructure.web.mapper;

import com.virtadity.manease.application.model.producer.ProducerRequest;
import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.infrastructure.web.dto.producer.ProducerRequestDTO;
import com.virtadity.manease.infrastructure.web.dto.producer.ProducerResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProducerMapper {
    ProducerRequest toProducerRequest(ProducerRequestDTO producerRequestDTO);
    ProducerResponseDTO toProducerResponseDTO(ProducerResponse producerResponse);
}
