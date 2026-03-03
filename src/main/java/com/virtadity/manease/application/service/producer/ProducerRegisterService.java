package com.virtadity.manease.application.service.producer;

import com.virtadity.manease.application.mapper.ProducerMapper;
import com.virtadity.manease.application.port.in.producer.ProducerRegisterInputBoundary;
import com.virtadity.manease.application.model.producer.ProducerRequest;
import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.application.port.out.producer.ProducerCreateOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProducerRegisterService implements ProducerRegisterInputBoundary {

    private final ProducerCreateOutputBoundary producerStorageRegister;
    private final ProducerMapper producerMapper;

    @Override
    public ProducerResponse execute(ProducerRequest producerRequest) {
        var producer = producerMapper.toProducer(producerRequest);
        var createdProducer = producerStorageRegister.create(producer);
        return producerMapper.toProducerResponse(createdProducer);
    }
}
