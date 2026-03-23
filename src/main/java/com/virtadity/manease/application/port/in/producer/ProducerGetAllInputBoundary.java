package com.virtadity.manease.application.port.in.producer;

import com.virtadity.manease.application.model.producer.ProducerResponse;

import java.util.List;

public interface ProducerGetAllInputBoundary {
    List<ProducerResponse> execute();
}
