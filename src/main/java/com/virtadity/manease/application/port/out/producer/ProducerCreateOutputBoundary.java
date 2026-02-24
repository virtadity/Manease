package com.virtadity.manease.application.port.out.producer;

import com.virtadity.manease.domain.model.Producer;

public interface ProducerCreateOutputBoundary {
    Producer execute(Producer producer);
}
