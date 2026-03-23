package com.virtadity.manease.application.port.in.producer;

import java.util.UUID;

public interface ProducerDeleteInputBoundary {
    void execute(UUID producerId);
}
