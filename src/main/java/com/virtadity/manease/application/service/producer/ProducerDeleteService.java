package com.virtadity.manease.application.service.producer;

import com.virtadity.manease.application.port.in.producer.ProducerDeleteInputBoundary;
import com.virtadity.manease.application.port.out.producer.ProducerDeleteOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProducerDeleteService implements ProducerDeleteInputBoundary {

    private final ProducerDeleteOutputBoundary producerStorageDelete;

    @Override
    public void execute(UUID producerId) {
        producerStorageDelete.delete(producerId);
    }
}
