package com.manease.producer.application.service.purchase;

import com.manease.producer.application.entity.purchase.PurchaseRequest;
import com.manease.producer.application.entity.purchase.PurchaseResponse;
import com.manease.producer.application.mapper.purchase.PurchaseMapper;
import com.manease.producer.application.port.out.purchase.PurchaseCreateOutputBoundary;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetOneOutputBoundary;
import com.manease.producer.application.service.purchase.actions.PurchaseCreateService;
import com.manease.producer.application.service.purchase.exception.PurchaseAlreadyExistException;
import com.manease.producer.domain.entity.purchase.Purchase;
import com.manease.producer.domain.entity.purchase.PurchaseStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TestPurchaseCreateService {

    private UUID createdStatusId;

    @Mock
    private PurchaseCreateOutputBoundary purchaseCreate;

    @Mock
    private PurchaseGetOneOutputBoundary purchaseGetOne;

    @Mock
    private PurchaseMapper purchaseMapper;

    private PurchaseCreateService purchaseCreateService;

    @BeforeEach
    void setUp() {
        createdStatusId = UUID.randomUUID();
        var purchaseStatusCreated = new PurchaseStatus(
                createdStatusId,
                "created",
                "test description"
        );

        purchaseCreateService = new PurchaseCreateService(
                purchaseStatusCreated,
                purchaseCreate,
                purchaseGetOne,
                purchaseMapper
        );
    }

    @Test
    void testPurchaseCreate() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var purchaseStatusId = createdStatusId;
        var purchaseRequest = new PurchaseRequest(id, producerId);
        var purchase = new Purchase(id, producerId, purchaseStatusId);
        var purchaseResponse = new PurchaseResponse(id, producerId, purchaseStatusId);

        when(purchaseMapper.toPurchase(any(), any())).thenReturn(purchase);
        when(purchaseGetOne.execute(any())).thenReturn(Optional.empty());
        when(purchaseCreate.execute(purchase)).thenReturn(purchase);
        when(purchaseMapper.toPurchaseResponse(purchase)).thenReturn(purchaseResponse);

        var actualPurchaseResponse = purchaseCreateService.execute(purchaseRequest);
        Assertions.assertEquals(actualPurchaseResponse, purchaseResponse);
    }

    @Test
    void testPurchaseCreateWhenAlreadyExist() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var purchaseStatusId = createdStatusId;
        var purchaseRequest = new PurchaseRequest(id, producerId);
        var purchase = new Purchase(id, producerId, purchaseStatusId);

        when(purchaseMapper.toPurchase(any(), any())).thenReturn(purchase);
        when(purchaseGetOne.execute(any())).thenReturn(Optional.of(purchase));

        Assertions.assertThrows(
                PurchaseAlreadyExistException.class,
                () -> purchaseCreateService.execute(purchaseRequest)
        );
    }

}
