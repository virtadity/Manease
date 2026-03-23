package com.virtadity.manease.application.port.out.producer;

import java.util.UUID;

public interface ProducerDeleteOutputBoundary {
    void delete(UUID producerId);
}
