package com.virtadity.manease.application.service.producer;

import com.virtadity.manease.application.mapper.ProducerMapper;
import com.virtadity.manease.application.model.producer.ProducerRequest;
import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.application.port.out.producer.ProducerCorrectOutputBoundary;
import com.virtadity.manease.domain.model.Producer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TestProducerCorrectService {

    @Mock
    private ProducerCorrectOutputBoundary producerCorrectOutputStorage;

    @Mock
    private ProducerMapper producerMapper;

    @InjectMocks
    private ProducerCorrectService producerCorrectService;

    @Test
    void testProducerCorrect() {
        var producerId = UUID.randomUUID();

        var updatedProducerName = "Золотой помидор";
        var updatedProducer = new Producer(producerId, updatedProducerName);

        var producerRequest = new ProducerRequest(producerId, updatedProducerName);

        var expectedProducerResponseUpdated = new ProducerResponse(producerId, updatedProducerName);

        when(producerCorrectOutputStorage.correct(producerId, updatedProducer)).thenReturn(updatedProducer);
        when(producerMapper.toProducerResponse(updatedProducer)).thenReturn(expectedProducerResponseUpdated);
        when(producerMapper.toProducer(producerRequest)).thenReturn(updatedProducer);

        var actualProducerResponseUpdated = producerCorrectService.execute(producerId, producerRequest);
        assertThat(actualProducerResponseUpdated)
                .isNotNull()
                .isEqualTo(expectedProducerResponseUpdated);
    }
}
