package com.manease.producer.infrastructure.database.dao;

import com.manease.producer.application.port.out.purchase.PurchaseCreateOutputBoundary;
import com.manease.producer.application.port.out.purchase.PurchaseSetStatusOutputBoundary;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetAllOutputBoundary;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetAllWithStatusOutputBoundary;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetOneOutputBoundary;
import com.manease.producer.domain.entity.purchase.Purchase;
import com.manease.producer.infrastructure.database.dao.exception.PurchaseEntityNotFoundException;
import com.manease.producer.infrastructure.database.dao.exception.PurchaseStatusEntityNotFoundException;
import com.manease.producer.infrastructure.database.entity.PurchaseEntity;
import com.manease.producer.infrastructure.database.mapper.PurchaseEntityMapper;
import com.manease.producer.infrastructure.database.repository.PurchaseRepository;
import com.manease.producer.infrastructure.database.repository.PurchaseStatusRepository;
import jakarta.transaction.Transactional;
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
        PurchaseGetOneOutputBoundary,
        PurchaseGetAllOutputBoundary,
        PurchaseGetAllWithStatusOutputBoundary {

    private final PurchaseEntityMapper purchaseEntityMapper;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseStatusRepository purchaseStatusRepository;

    @Override
    public Purchase create(Purchase purchase) {
        var purchaseEntity = purchaseEntityMapper.toPurchaseEntity(purchase);
        var createdPurchaseEntity = purchaseRepository.save(purchaseEntity);
        return purchaseEntityMapper.toPurchase(createdPurchaseEntity);
    }

    // The follow method has been divided for the reduction the time used for the transaction.
    // It's not as noticeable as in larger projects.
    @Override
    public Purchase setStatus(UUID purchaseId, UUID purchaseStatusId) {
        var updatedPurchase = this.updateStatus(purchaseId, purchaseStatusId);
        return purchaseEntityMapper.toPurchase(updatedPurchase);
    }

    @Transactional
    private PurchaseEntity updateStatus(UUID purchaseId, UUID purchaseStatusId) {
        var purchaseEntity = purchaseRepository.findById(purchaseId).orElseThrow(
                () -> PurchaseEntityNotFoundException.withId(purchaseId)
        );

        var purchaseStatusEntity = purchaseStatusRepository.findById(purchaseStatusId).orElseThrow(
                () -> PurchaseStatusEntityNotFoundException.withId(purchaseStatusId)
        );

        purchaseEntity.setPurchaseStatusEntity(purchaseStatusEntity);
        return purchaseRepository.save(purchaseEntity);
    }

    @Override
    public Optional<Purchase> getOne(UUID id) {
        var purchaseEntity = purchaseRepository.findById(id);
        return purchaseEntity.map(purchaseEntityMapper::toPurchase);
    }

    @Override
    @Transactional
    public List<Purchase> getAllWith(UUID producerId) {
        var purchaseEntityList = purchaseRepository.findAllWithProducer(producerId);
        return purchaseEntityMapper.toPurchaseList(purchaseEntityList);
    }

    @Override
    @Transactional
    public List<Purchase> getAllWith(UUID producerId, UUID purchaseStatusId) {
        var purchaseEntityList = purchaseRepository.findAllWithProducerAndStatus(producerId, purchaseStatusId);
        return purchaseEntityMapper.toPurchaseList(purchaseEntityList);
    }
}
