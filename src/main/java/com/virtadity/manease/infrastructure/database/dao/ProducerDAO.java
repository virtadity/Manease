package com.virtadity.manease.infrastructure.database.dao;

import com.virtadity.manease.application.port.out.producer.*;
import com.virtadity.manease.domain.model.Producer;
import com.virtadity.manease.infrastructure.database.mapper.ProducerEntityMapper;
import com.virtadity.manease.infrastructure.database.repository.ProducerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ProducerDAO implements
        ProducerCorrectOutputBoundary,
        ProducerCreateOutputBoundary,
        ProducerDeleteOutputBoundary,
        ProducerGetOneOutputBoundary,
        ProducerGetAllOutputBoundary
{
    private final ProducerRepository producerRepository;
    private final ProducerEntityMapper producerEntityMapper;

    @Override
    @Transactional
    public Producer correct(UUID producerId, Producer producer) {
        var producerEntity = producerRepository
                .findById(producerId).orElseGet(() -> producerEntityMapper.toProducerEntity(producer));
        producerEntityMapper.updateFromProducer(producerEntity, producer);
        var correctedProducerEntity = producerRepository.save(producerEntity);
        return producerEntityMapper.toProducer(correctedProducerEntity);
    }

    @Override
    public Producer create(Producer producer) {
        var producerEntity = producerEntityMapper.toProducerEntity(producer);
        var createdProducerEntity = producerRepository.save(producerEntity);
        return producerEntityMapper.toProducer(createdProducerEntity);
    }


    @Override
    @Transactional
    public void delete(UUID producerId) {
        producerRepository.deleteById(producerId);
    }

    @Override
    public Optional<Producer> getOne(UUID producerId) {
        var producerEntity = producerRepository.findById(producerId);
        return producerEntity.map(producerEntityMapper::toProducer);
    }

    @Override
    @Transactional
    public List<Producer> getAll() {
        var producerEntities = producerRepository.findAll();
        return producerEntityMapper.toProducerList(producerEntities);
    }
}
