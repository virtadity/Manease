package com.virtadity.manease.infrastructure.database.dao;

import com.virtadity.manease.application.port.out.purchase.line.*;
import com.virtadity.manease.domain.model.PurchaseLine;
import com.virtadity.manease.infrastructure.database.dao.exception.ProductEntityNotFoundException;
import com.virtadity.manease.infrastructure.database.dao.exception.PurchaseEntityNotFoundException;
import com.virtadity.manease.infrastructure.database.entity.ProductEntity;
import com.virtadity.manease.infrastructure.database.entity.PurchaseEntity;
import com.virtadity.manease.infrastructure.database.entity.PurchaseLineId;
import com.virtadity.manease.infrastructure.database.mapper.PurchaseLineEntityMapper;
import com.virtadity.manease.infrastructure.database.repository.ProductRepository;
import com.virtadity.manease.infrastructure.database.repository.PurchaseLineRepository;
import com.virtadity.manease.infrastructure.database.repository.PurchaseRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class PurchaseLineDAO implements
        PurchaseLineCreateOutputBoundary,
        PurchaseLineCorrectOutputBoundary,
        PurchaseLineGetAllOutputBoundary,
        PurchaseLineGetOneOutputBoundary,
        PurchaseLineDeleteOutputBoundary,
        PurchaseLineGetAllOfPurchaseOutputBoundary
{
    private final PurchaseLineEntityMapper purchaseLineEntityMapper;
    private final PurchaseLineRepository purchaseLineRepository;
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public PurchaseLine create(PurchaseLine purchaseLine) {
        var purchaseId = purchaseLine.purchaseId();
        var productId = purchaseLine.productId();
        var purchaseEntity = getPurchaseEntityById(purchaseId);
        var productEntity = getProductEntityById(productId);
        var purchaseLineId = new PurchaseLineId(purchaseId, productId);
        var purchaseLineEntity = purchaseLineEntityMapper
                .toPurchaseLineEntity(
                        purchaseLine,
                        purchaseLineId,
                        purchaseEntity,
                        productEntity
                );
        var createdPurchaseLineEntity = purchaseLineRepository.save(purchaseLineEntity);
        return purchaseLineEntityMapper.toPurchaseLine(createdPurchaseLineEntity);
    }

    private PurchaseEntity getPurchaseEntityById(UUID purchaseId) {
        return purchaseRepository
                .findById(purchaseId)
                .orElseThrow(() -> PurchaseEntityNotFoundException.withSuchId(purchaseId));
    }

    private ProductEntity getProductEntityById(UUID productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> ProductEntityNotFoundException.withSuchId(productId));
    }

    @Transactional
    @Override
    public PurchaseLine correct(UUID purchaseId, UUID productId, PurchaseLine purchaseLine) {
        var purchaseEntity = getPurchaseEntityById(purchaseId);
        var productEntity = getProductEntityById(productId);
        var purchaseLineId = new PurchaseLineId(purchaseId, productId);
        var purchaseLineEntity = purchaseLineRepository
                .findById(purchaseLineId)
                .orElseGet(() -> purchaseLineEntityMapper
                        .toPurchaseLineEntity(
                                purchaseLine,
                                purchaseLineId,
                                purchaseEntity,
                                productEntity
                        )
                );

        purchaseLineEntityMapper.updateFromPurchaseLine(
                purchaseLineEntity,
                purchaseLine,
                purchaseEntity,
                productEntity
        );

        var correctedPurchaseLineEntity = purchaseLineRepository.save(purchaseLineEntity);
        return purchaseLineEntityMapper.toPurchaseLine(correctedPurchaseLineEntity);
    }

    @Override
    public List<PurchaseLine> getAll() {
        var purchaseLineEntityList = purchaseLineRepository.findAll();
        return purchaseLineEntityMapper.toPurchaseLineList(purchaseLineEntityList);
    }

    @Override
    public Optional<PurchaseLine> getOne(UUID purchaseId, UUID productId) {
        var purchaseLineId = new PurchaseLineId(purchaseId, productId);
        return purchaseLineRepository.findById(purchaseLineId).map(purchaseLineEntityMapper::toPurchaseLine);
    }

    @Override
    public void delete(UUID purchaseId, UUID productId) {
        var purchaseLineId = new PurchaseLineId(purchaseId, productId);
        purchaseLineRepository.deleteById(purchaseLineId);
    }

    @Override
    public List<PurchaseLine> getAllOfPurchase(UUID purchaseId) {
        return purchaseLineEntityMapper
                .toPurchaseLineList(
                        purchaseLineRepository.getPurchaseLinesOfPurchase(purchaseId)
                );
    }
}
