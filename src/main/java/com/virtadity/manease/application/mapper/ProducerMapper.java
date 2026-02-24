package com.virtadity.manease.application.mapper;

import com.virtadity.manease.application.model.producer.ProducerRequest;
import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.domain.model.Producer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProducerMapper {
    Producer toProducer(ProducerRequest producerRequest);
    List<Producer> toProducers(List<ProducerRequest> producerRequests);

    ProducerResponse toProducerResponse(Producer producer);
    List<ProducerResponse> toProducerResponseList(List<Producer> producers);

;

}

