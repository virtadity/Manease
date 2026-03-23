package com.virtadity.manease.application.port.out.producer;

import com.virtadity.manease.domain.model.Producer;

import java.util.Optional;
import java.util.UUID;

public interface ProducerGetOneOutputBoundary {
    Optional<Producer> getOne(UUID producerId);
}
