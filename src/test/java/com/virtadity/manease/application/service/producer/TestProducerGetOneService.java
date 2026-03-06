package com.virtadity.manease.application.service.producer;

import com.virtadity.manease.application.mapper.ProducerMapper;
import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.application.port.out.producer.ProducerGetOneOutputBoundary;
import com.virtadity.manease.application.service.producer.exception.ProducerNotFoundException;
import com.virtadity.manease.domain.model.Producer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestProducerGetOneService {
    @Mock
    private ProducerGetOneOutputBoundary producerGetOneOutputBoundary;

    @Mock
    private ProducerMapper producerMapper;

    @InjectMocks
    private ProducerGetOneService producerGetOneService;

    @Test
    void testProducerGetOne() {
        var producerId = UUID.randomUUID();
        var producerName = "Магнит";
        var producer = new Producer(producerId, producerName);
        var producerResponse = new ProducerResponse(producerId, producerName);

        var otherProducerId = UUID.randomUUID();

        when(producerGetOneOutputBoundary.getOne(producerId)).thenReturn(Optional.of(producer));
        when(producerGetOneOutputBoundary.getOne(otherProducerId)).thenReturn(Optional.empty());
        when(producerMapper.toProducerResponse(producer)).thenReturn(producerResponse);

        var actualProducerResponse = producerGetOneService.execute(producerId);
        assertThat(actualProducerResponse).isNotNull().isEqualTo(producerResponse);

        Assertions.assertThrows(ProducerNotFoundException.class, () -> producerGetOneService.execute(otherProducerId));
    }

}
