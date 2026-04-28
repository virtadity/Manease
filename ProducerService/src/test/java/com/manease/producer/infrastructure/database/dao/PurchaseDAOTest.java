package com.manease.producer.infrastructure.database.dao;

import com.manease.producer.application.entity.response.PurchaseStatusResponse;
import com.manease.producer.domain.entity.Purchase;
import com.manease.producer.infrastructure.database.dao.exception.PurchaseNotFoundException;
import com.manease.producer.infrastructure.database.dao.exception.PurchaseStatusNotFoundException;
import com.manease.producer.infrastructure.database.entity.PurchaseEntity;
import com.manease.producer.infrastructure.database.entity.PurchaseStatusEntity;
import com.manease.producer.infrastructure.database.mapper.PurchaseEntityMapper;
import com.manease.producer.infrastructure.database.repository.PurchaseRepository;
import com.manease.producer.infrastructure.database.repository.PurchaseStatusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PurchaseDAOTest {

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private PurchaseStatusRepository purchaseStatusRepository;

    @Mock
    private PurchaseEntityMapper purchaseEntityMapper;

    @InjectMocks
    private PurchaseDAO purchaseDAO;

    @Test
    public void testCreatePurchaseButPurchaseStatusNotFound() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var statusId = UUID.randomUUID();

        var purchase = new Purchase(id, producerId, statusId);

        when(purchaseStatusRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(PurchaseStatusNotFoundException.class, () -> {
            purchaseDAO.createPurchase(purchase);
        });
    }

    @Test
    public void testCreatePurchase() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var statusId = UUID.randomUUID();
        var statusName = "Test purchase status name";
        var statusDescription = "Test purchase status description";

        var purchase = new Purchase(id, producerId, statusId);
        var purchaseStatusEntity = new PurchaseStatusEntity(statusId, statusName, statusDescription);
        var mappedPurchaseEntity = new PurchaseEntity(id, producerId, purchaseStatusEntity);
        var createdPurchaseEntity = new PurchaseEntity(id, producerId, purchaseStatusEntity);
        var expectedCreatedPurchase = new Purchase(id, producerId, statusId);

        when(purchaseStatusRepository.findById(statusId)).thenReturn(Optional.of(purchaseStatusEntity));
        when(purchaseEntityMapper.toPurchaseEntity(purchase, purchaseStatusEntity)).thenReturn(mappedPurchaseEntity);
        when(purchaseRepository.save(mappedPurchaseEntity)).thenReturn(createdPurchaseEntity);
        when(purchaseEntityMapper.toPurchase(createdPurchaseEntity)).thenReturn(expectedCreatedPurchase);

        var actualCreatedPurchase = purchaseDAO.createPurchase(purchase);
        assertThat(actualCreatedPurchase)
                .isNotNull()
                .isEqualTo(expectedCreatedPurchase);
    }

    @Test
    public void testSetStatusToPurchaseButPurchaseNotFound() {
        var id = UUID.randomUUID();
        var statusId = UUID.randomUUID();

        when(purchaseRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(PurchaseNotFoundException.class, () -> purchaseDAO.setStatusToPurchase(id, statusId));
    }

    @Test
    public void testSetStatusToPurchaseButPurchaseStatusNotFound() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var statusId = UUID.randomUUID();
        var statusName = "Test purchase status name";
        var statusDescription = "Test purchase status description";

        var updatedStatusId = UUID.randomUUID();
        var purchaseStatusEntity = new PurchaseStatusEntity(statusId, statusName, statusDescription);
        var purchaseEntity = new PurchaseEntity(id, producerId, purchaseStatusEntity);

        when(purchaseRepository.findById(any())).thenReturn(Optional.of(purchaseEntity));
        when(purchaseStatusRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(PurchaseStatusNotFoundException.class, () -> purchaseDAO.setStatusToPurchase(id, updatedStatusId));
    }
}
