package com.virtadity.manease.application.port.in.producer;

import com.virtadity.manease.application.model.producer.ProducerResponse;

import java.util.Optional;
import java.util.UUID;

public interface ProducerGetOneInputBoundary {
    ProducerResponse execute(UUID producerId);
}
