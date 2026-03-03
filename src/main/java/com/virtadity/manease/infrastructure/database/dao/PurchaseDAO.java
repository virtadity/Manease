package com.virtadity.manease.infrastructure.database.dao;

import com.virtadity.manease.application.port.out.purchase.*;
import com.virtadity.manease.domain.model.Purchase;
import com.virtadity.manease.infrastructure.database.dao.exception.ProducerNotFoundException;
import com.virtadity.manease.infrastructure.database.entity.ProducerEntity;
import com.virtadity.manease.infrastructure.database.mapper.PurchaseEntityMapper;
import com.virtadity.manease.infrastructure.database.repository.ProducerRepository;
import com.virtadity.manease.infrastructure.database.repository.PurchaseRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class PurchaseDAO implements
        PurchaseCreateOutputBoundary,
        PurchaseCorrectOutputBoundary,
        PurchaseGetOneOutputBoundary,
        PurchaseGetAllOutputBoundary,
        PurchaseDeleteOutputBoundary
{
    private final PurchaseRepository purchaseRepository;
    private final ProducerRepository producerRepository;
    private final PurchaseEntityMapper purchaseEntityMapper;

    @Override
    public Purchase create(Purchase purchase) {
        var producerEntity = this.getProducerById(purchase.producerId());
        var purchaseEntity = purchaseEntityMapper.toPurchaseEntity(purchase, producerEntity);
        var createdPurchaseEntity = purchaseRepository.save(purchaseEntity);
        return purchaseEntityMapper.toPurchase(createdPurchaseEntity);
    }

    @Override
    @Transactional
    public Purchase correct(UUID purchaseId, Purchase purchase) {
        var producerEntity = this.getProducerById(purchase.producerId());
        var purchaseEntity = purchaseRepository
                .findById(purchaseId)
                .orElseGet(() -> purchaseEntityMapper.toPurchaseEntity(purchase, producerEntity));

        purchaseEntityMapper.updateFromPurchase(purchaseEntity, purchase, producerEntity);
        var correctedPurchaseEntity = purchaseRepository.save(purchaseEntity);
        return purchaseEntityMapper.toPurchase(correctedPurchaseEntity);
    }

    @Override
    public Optional<Purchase> getOne(UUID purchaseId) {
        return purchaseRepository.findById(purchaseId).map(purchaseEntityMapper::toPurchase);
    }

    @Override
    @Transactional
    public List<Purchase> getAll() {
        var purchaseEntityList = purchaseRepository.findAll();
        return purchaseEntityMapper.toPurchaseList(purchaseEntityList);
    }

    @Override
    public void delete(UUID purchaseId) {
        purchaseRepository.deleteById(purchaseId);
    }

    private ProducerEntity getProducerById(UUID producerId) {
        return producerRepository
                .findById(producerId)
                .orElseThrow(
                        () -> ProducerNotFoundException.withSuchId(producerId)
                );
    }
}
