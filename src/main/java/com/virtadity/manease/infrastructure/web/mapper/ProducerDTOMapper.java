package com.virtadity.manease.infrastructure.web.mapper;

import com.virtadity.manease.application.model.producer.ProducerRequest;
import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.infrastructure.web.dto.producer.ProducerRequestDTO;
import com.virtadity.manease.infrastructure.web.dto.producer.ProducerResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProducerDTOMapper {
    @Mapping(source = "id", target = "producerId")
    ProducerRequest toProducerRequest(ProducerRequestDTO producerRequestDTO);

    @Mapping(source = "producerId", target = "id")
    ProducerResponseDTO toProducerResponseDTO(ProducerResponse producerResponse);
}
