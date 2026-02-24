package com.virtadity.manease.application.port.out.producer;

import java.util.UUID;

public interface ProducerDeleteOutputBoundary {
    void execute(UUID producerId);
}
