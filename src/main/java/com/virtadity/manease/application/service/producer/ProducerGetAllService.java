package com.virtadity.manease.application.service.producer;

import com.virtadity.manease.application.mapper.ProducerMapper;
import com.virtadity.manease.application.model.producer.ProducerResponse;
import com.virtadity.manease.application.port.in.producer.ProducerGetAllInputBoundary;
import com.virtadity.manease.application.port.out.producer.ProducerGetAllOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProducerGetAllService implements ProducerGetAllInputBoundary {
    private final ProducerGetAllOutputBoundary producerStorageGetAll;
    private final ProducerMapper producerMapper;

    @Override
    public List<ProducerResponse> execute() {
        var producerList = producerStorageGetAll.execute();
        return producerMapper.toProducerResponseList(producerList);
    }
}
