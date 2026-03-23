package com.virtadity.manease.application.port.in.producer;

import com.virtadity.manease.application.model.producer.ProducerRequest;
import com.virtadity.manease.application.model.producer.ProducerResponse;

import java.util.UUID;

public interface ProducerCorrectInputBoundary {
    ProducerResponse execute(UUID productId, ProducerRequest productRequest);
}
