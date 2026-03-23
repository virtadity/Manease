package com.virtadity.manease.application.mapper;


import com.virtadity.manease.application.model.producer.ProducerRequest;
import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.domain.model.Producer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;


public class TestProducerMapper {

    private final ProducerMapper producerMapper = new ProducerMapperImpl();

    @Test
    void testProducerRequestToProducer() {
        var producerId = UUID.randomUUID();
        var producerName = "Магнит";
        var producerRequest = new ProducerRequest(producerId, producerName);

        var expectedProducer = new Producer(producerId, producerName);
        var actualProducer = producerMapper.toProducer(producerRequest);

        assertThat(actualProducer)
                .isNotNull()
                .isEqualTo(expectedProducer);
    }

    @Test
    void testProducerRequestWithNullIdToProducer() {
        var producerName = "Магнит";
        var producerRequest = new ProducerRequest(null, producerName);

        var actualProducer = producerMapper.toProducer(producerRequest);
        assertThat(actualProducer).isNotNull();

        var actualProducerId = actualProducer.producerId();
        assertThat(actualProducerId).isNotNull();

        var actualProducerName = actualProducer.name();
        assertThat(actualProducerName).isNotNull();
    }

    @Test
    void testProducerToProducerResponse() {
        var producerId = UUID.randomUUID();
        var producerName = "Магнит";
        var producer = new Producer(producerId, producerName);

        var expectedProducerResponse = new ProducerResponse(producerId, producerName);
        var actualProducerResponse = producerMapper.toProducerResponse(producer);

        assertThat(actualProducerResponse).isNotNull().isEqualTo(expectedProducerResponse);
    }
}
