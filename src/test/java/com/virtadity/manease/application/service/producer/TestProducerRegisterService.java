package com.virtadity.manease.application.service.producer;

import com.virtadity.manease.application.mapper.ProducerMapper;
import com.virtadity.manease.application.model.producer.ProducerRequest;
import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.application.port.out.producer.ProducerCreateOutputBoundary;
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
public class TestProducerRegisterService {

    @Mock
    private ProducerMapper producerMapper;

    @Mock
    private ProducerCreateOutputBoundary producerCreateOutputPort;

    @InjectMocks
    private ProducerRegisterService producerRegisterService;

    @Test
    void testProducerRegister() {
        var producerId = UUID.randomUUID();
        var producerName = "Магнит";

        var producerRequest = new ProducerRequest(producerId, producerName);
        var producer = new Producer(producerId, producerName);
        var producerResponse = new ProducerResponse(producerId, producerName);

        when(producerMapper.toProducer(producerRequest)).thenReturn(producer);
        when(producerMapper.toProducerResponse(producer)).thenReturn(producerResponse);
        when(producerCreateOutputPort.create(producer)).thenReturn(producer);

        var actualResponse = producerRegisterService.execute(producerRequest);
        assertThat(actualResponse).isNotNull().isEqualTo(producerResponse);
    }

    @Test
    void testProducerRegisterWithNullId() {
        var producerId = UUID.randomUUID();
        var producerName = "Магнит";

        var producerRequest = new ProducerRequest(null, producerName);
        var producer = new Producer(producerId, producerName);
        var producerResponse = new ProducerResponse(producerId, producerName);

        when(producerMapper.toProducer(producerRequest)).thenReturn(producer);
        when(producerMapper.toProducerResponse(producer)).thenReturn(producerResponse);
        when(producerCreateOutputPort.create(producer)).thenReturn(producer);

        var actualResponse = producerRegisterService.execute(producerRequest);
        assertThat(actualResponse).isNotNull().isEqualTo(producerResponse);
    }

}
