package com.virtadity.manease.application.service.producer;

import com.virtadity.manease.application.mapper.ProducerMapper;
import com.virtadity.manease.application.model.producer.ProducerRequest;
import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.application.port.in.producer.ProducerCorrectInputBoundary;
import com.virtadity.manease.application.port.out.producer.ProducerCorrectOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProducerCorrectService implements ProducerCorrectInputBoundary {

    private final ProducerCorrectOutputBoundary producerStorageCorrect;
    private final ProducerMapper producerMapper;

    @Override
    public ProducerResponse execute(UUID productId, ProducerRequest productRequest) {
        var producer = producerMapper.toProducer(productRequest);
        var correctedProducer = producerStorageCorrect.execute(productId, producer);
        return producerMapper.toProducerResponse(correctedProducer);
    }
}
