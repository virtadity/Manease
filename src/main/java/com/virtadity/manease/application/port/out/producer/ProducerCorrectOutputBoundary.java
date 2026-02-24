package com.virtadity.manease.application.port.out.producer;

import com.virtadity.manease.domain.model.Producer;

import java.util.UUID;

public interface ProducerCorrectOutputBoundary {
    Producer execute(UUID producerId, Producer producer);
}
