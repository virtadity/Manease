package com.virtadity.manease.application.service.producer;

import com.virtadity.manease.application.mapper.ProducerMapper;
import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.application.port.out.producer.ProducerGetAllOutputBoundary;
import com.virtadity.manease.domain.model.Producer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestProducerGetAllService {

    @Mock
    private ProducerGetAllOutputBoundary producerGetAllStorage;

    @Mock
    private ProducerMapper producerMapper;

    @InjectMocks
    private ProducerGetAllService producerGetAllService;

    private final List<UUID> producerIdList = List.of(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
    );

    private final List<String> producerNameList = List.of(
            "Магнит",
            "Пятёрочка",
            "Метро",
            "Перекрёсток"
    );

    private List<Producer> producerList;
    private List<ProducerResponse> producerResponseList;

    @BeforeEach
    void setUp() {
        producerList = setUpProducerList(producerIdList, producerNameList);
        producerResponseList = setUpProducerResponseList(producerIdList, producerNameList);
    }

    private List<Producer> setUpProducerList(List<UUID> producerIds, List<String> producerNames) {
        var producers = new ArrayList<Producer>(producerIds.size());
        for (int index = 0; index < producerIds.size(); index++) {
            producers.add(new Producer(producerIds.get(index), producerNames.get(index)));
        }
        return producers;
    }

    private List<ProducerResponse> setUpProducerResponseList(List<UUID> producerIds, List<String> producerNames) {
        var producerResponses = new ArrayList<ProducerResponse>(producerIds.size());
        for (int index = 0; index < producerIds.size(); index++) {
            producerResponses.add(new ProducerResponse(producerIds.get(index), producerNames.get(index)));
        }
        return producerResponses;
    }

    @Test
    void testProducerGetAll() {
        when(producerMapper.toProducerResponseList(producerList)).thenReturn(producerResponseList);
        when(producerGetAllStorage.getAll()).thenReturn(producerList);

        var actualProducerResponseList = producerGetAllService.execute();
        assertThat(actualProducerResponseList).isNotNull();
    }
}
