package com.virtadity.manease.application.port.out.producer;

import com.virtadity.manease.domain.model.Producer;

import java.util.List;

public interface ProducerGetAllOutputBoundary {
    List<Producer> getAll();
}
