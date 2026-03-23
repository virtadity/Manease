package com.virtadity.manease.application.port.in.producer;

import com.virtadity.manease.application.model.producer.ProducerRequest;
import com.virtadity.manease.application.model.producer.ProducerResponse;

public interface ProducerRegisterInputBoundary {
    ProducerResponse execute(ProducerRequest producerRequest);
}
