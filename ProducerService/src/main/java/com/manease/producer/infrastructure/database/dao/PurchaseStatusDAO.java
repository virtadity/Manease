package com.manease.producer.infrastructure.database.dao;

import com.manease.producer.application.port.out.purchase.status.PurchaseStatusCreateOutputBoundary;
import com.manease.producer.application.port.out.purchase.status.PurchaseStatusGetAllOutputBoundary;
import com.manease.producer.application.port.out.purchase.status.PurchaseStatusGetByNameOutputBoundary;
import com.manease.producer.application.port.out.purchase.status.PurchaseStatusGetOneOutputBoundary;
import com.manease.producer.domain.entity.purchase.PurchaseStatus;
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
        PurchaseStatusGetOneOutputBoundary,
        PurchaseStatusGetAllOutputBoundary,
        PurchaseStatusGetByNameOutputBoundary {

    private final PurchaseStatusEntityMapper purchaseStatusEntityMapper;
    private final PurchaseStatusRepository purchaseStatusRepository;

    @Override
    public PurchaseStatus create(PurchaseStatus purchaseStatus) {
        var purchaseStatusEntity = purchaseStatusEntityMapper.toPurchaseStatusEntity(purchaseStatus);
        var createdPurchaseStatusEntity = purchaseStatusRepository.save(purchaseStatusEntity);
        return purchaseStatusEntityMapper.toPurchaseStatus(createdPurchaseStatusEntity);
    }

    @Override
    public Optional<PurchaseStatus> getOne(UUID id) {
        var purchaseStatusEntity = purchaseStatusRepository.findById(id);
        return purchaseStatusEntity.map(purchaseStatusEntityMapper::toPurchaseStatus);
    }

    @Override
    public List<PurchaseStatus> getAll() {
        var purchaseStatusEntityList = purchaseStatusRepository.findAll();
        return purchaseStatusEntityMapper.toPurchaseStatusList(purchaseStatusEntityList);
    }

    @Override
    public Optional<PurchaseStatus> getByName(String name) {
        var purchaseStatusEntity = purchaseStatusRepository.findByName(name);
        return purchaseStatusEntity.map(purchaseStatusEntityMapper::toPurchaseStatus);
    }

}
