package com.manease.producer.infrastructure.database.dao;

import com.manease.producer.application.port.out.purchase.actions.PurchaseCreateOutputBoundary;
import com.manease.producer.application.port.out.purchase.actions.PurchaseSetStatusOutputBoundary;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetAllOutputBoundary;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetOneOutputBoundary;
import com.manease.producer.domain.entity.Purchase;
import com.manease.producer.infrastructure.database.dao.exception.PurchaseNotFoundException;
import com.manease.producer.infrastructure.database.dao.exception.PurchaseStatusNotFoundException;
import com.manease.producer.infrastructure.database.mapper.PurchaseEntityMapper;
import com.manease.producer.infrastructure.database.repository.PurchaseRepository;
import com.manease.producer.infrastructure.database.repository.PurchaseStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PurchaseDAO implements
        PurchaseCreateOutputBoundary,
        PurchaseSetStatusOutputBoundary,
        PurchaseGetAllOutputBoundary,
        PurchaseGetOneOutputBoundary
{

    private final PurchaseRepository purchaseRepository;
    private final PurchaseStatusRepository purchaseStatusRepository;
    private final PurchaseEntityMapper purchaseEntityMapper;

    @Override
    public Purchase createPurchase(Purchase purchase) {
        var statusId = purchase.statusId();
        var purchaseStatusEntity = purchaseStatusRepository
                .findById(statusId)
                .orElseThrow(() -> PurchaseStatusNotFoundException.withId(statusId));

        var purchaseEntity = purchaseEntityMapper.toPurchaseEntity(purchase, purchaseStatusEntity);
        var createdPurchaseEntity = purchaseRepository.save(purchaseEntity);
        return purchaseEntityMapper.toPurchase(createdPurchaseEntity);
    }

    @Override
    public Purchase setStatusToPurchase(UUID purchaseId, UUID purchaseStatusId) {
        var purchaseEntity = purchaseRepository
                .findById(purchaseStatusId)
                .orElseThrow(() -> PurchaseNotFoundException.withId(purchaseId));

        var purchaseStatusEntity = purchaseStatusRepository
                .findById(purchaseStatusId)
                .orElseThrow(() -> PurchaseStatusNotFoundException.withId(purchaseStatusId));

        purchaseEntity.setPurchaseStatus(purchaseStatusEntity);
        var updatedPurchaseEntity = purchaseRepository.save(purchaseEntity);
        return purchaseEntityMapper.toPurchase(updatedPurchaseEntity);
    }

    @Override
    public Optional<Purchase> getOneById(UUID id) {
        var purchaseEntity = purchaseRepository.findById(id);
        return purchaseEntity.map(purchaseEntityMapper::toPurchase);
    }

    @Override
    public List<Purchase> getAll(UUID producerId) {
        return purchaseRepository
                .getAllWithProducer(producerId)
                .stream()
                .map(purchaseEntityMapper::toPurchase)
                .toList();
    }
}
