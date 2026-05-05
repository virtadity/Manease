package com.manease.producer.infrastructure.database.dao;

import com.manease.producer.application.port.out.purchase.status.actions.PurchaseStatusCreateOutputBoundary;
import com.manease.producer.application.port.out.purchase.status.getters.PurchaseStatusGetAllOutputBoundary;
import com.manease.producer.application.port.out.purchase.status.getters.PurchaseStatusGetByNameOutputBoundary;
import com.manease.producer.application.port.out.purchase.status.getters.PurchaseStatusGetOneOutputBoundary;
import com.manease.producer.domain.entity.PurchaseStatus;
import com.manease.producer.infrastructure.database.mapper.PurchaseStatusEntityMapper;
import com.manease.producer.infrastructure.database.repository.PurchaseStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PurchaseStatusDAO implements
        PurchaseStatusCreateOutputBoundary,
        PurchaseStatusGetAllOutputBoundary,
        PurchaseStatusGetOneOutputBoundary,
        PurchaseStatusGetByNameOutputBoundary
{

    private final PurchaseStatusRepository purchaseStatusRepository;
    private final PurchaseStatusEntityMapper purchaseStatusEntityMapper;

    @Override
    public PurchaseStatus createPurchaseStatus(PurchaseStatus purchaseStatus) {
        var purchaseStatusEntity = purchaseStatusEntityMapper.toPurchaseStatusEntity(purchaseStatus);
        var createdPurchaseStatusEntity = purchaseStatusRepository.save(purchaseStatusEntity);
        return purchaseStatusEntityMapper.toPurchaseStatus(createdPurchaseStatusEntity);
    }

    @Override
    public List<PurchaseStatus> getAll() {
        return purchaseStatusRepository
                .findAll()
                .stream()
                .map(purchaseStatusEntityMapper::toPurchaseStatus)
                .toList();
    }

    @Override
    public Optional<PurchaseStatus> getOneById(UUID id) {
        return purchaseStatusRepository
                .findById(id)
                .map(purchaseStatusEntityMapper::toPurchaseStatus);
    }

    @Override
    public Optional<PurchaseStatus> getOneByName(String name) {
        return purchaseStatusRepository.findByName(name).map(purchaseStatusEntityMapper::toPurchaseStatus);
    }
}
