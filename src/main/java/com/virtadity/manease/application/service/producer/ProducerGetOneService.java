package com.virtadity.manease.application.service.producer;

import com.virtadity.manease.application.mapper.ProducerMapper;
import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.application.port.in.producer.ProducerGetOneInputBoundary;
import com.virtadity.manease.application.port.out.producer.ProducerGetOneOutputBoundary;
import com.virtadity.manease.application.service.producer.exception.ProducerNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProducerGetOneService implements ProducerGetOneInputBoundary {
    private final ProducerGetOneOutputBoundary producerStorageGetOne;
    private final ProducerMapper producerMapper;

    @Override
    public ProducerResponse execute(UUID producerId) {
        var producer = producerStorageGetOne
                .getOne(producerId)
                .orElseThrow(()-> ProducerNotFoundException.withId(producerId));
        return producerMapper.toProducerResponse(producer);
    }
}
