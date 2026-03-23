package com.virtadity.manease.application.service.producer;

import com.virtadity.manease.application.port.out.producer.ProducerDeleteOutputBoundary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestProducerDeleteService {
    @Mock
    private ProducerDeleteOutputBoundary producerDeleteStorage;

    @InjectMocks
    private ProducerDeleteService producerDeleteService;

    @Test
    void testProducerDelete() {
        var producerId = UUID.randomUUID();
        producerDeleteService.execute(producerId);
        verify(producerDeleteStorage, Mockito.times(1)).delete(producerId);
    }
}
