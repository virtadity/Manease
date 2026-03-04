package com.virtadity.manease.application.mapper;

import com.virtadity.manease.application.model.producer.ProducerRequest;
import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.domain.model.Producer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProducerMapper {
    @Mapping(
            source="producerId",
            target = "producerId",
            defaultExpression = "java(UUID.randomUUID())"
    )
    Producer toProducer(ProducerRequest producerRequest);
    ProducerResponse toProducerResponse(Producer producer);
    List<ProducerResponse> toProducerResponseList(List<Producer> producers);
}

