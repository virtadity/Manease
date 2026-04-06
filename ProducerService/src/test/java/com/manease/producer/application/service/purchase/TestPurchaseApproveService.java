package com.manease.producer.application.service.purchase;

import com.manease.producer.application.entity.purchase.PurchaseResponse;
import com.manease.producer.application.mapper.purchase.PurchaseMapper;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetOneOutputBoundary;
import com.manease.producer.application.port.out.purchase.PurchaseSetStatusOutputBoundary;
import com.manease.producer.application.service.purchase.actions.PurchaseApproveService;
import com.manease.producer.application.service.purchase.exception.ProducerCannotApprovePurchaseException;
import com.manease.producer.application.service.purchase.exception.PurchaseCannotBeApprovedException;
import com.manease.producer.application.service.purchase.exception.PurchaseDoesNotExistException;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestPurchaseApproveService {

    private UUID approvedStatusId;
    private UUID createdStatusId;

    @Mock
    private PurchaseGetOneOutputBoundary purchaseGetOne;

    @Mock
    private PurchaseSetStatusOutputBoundary purchaseSetStatus;

    @Mock
    private PurchaseMapper purchaseMapper;

    private PurchaseApproveService purchaseApproveService;

    @BeforeEach
    void setUp() {
        approvedStatusId = UUID.randomUUID();
        var purchaseStatusApproved = new PurchaseStatus(
                approvedStatusId,
                "approved",
                "test description"
        );

        createdStatusId = UUID.randomUUID();
        var purchaseStatusCreated = new PurchaseStatus(
                createdStatusId,
                "created",
                "test description"
        );

        purchaseApproveService = new PurchaseApproveService(
                purchaseStatusApproved,
                purchaseStatusCreated,
                purchaseGetOne,
                purchaseSetStatus,
                purchaseMapper
        );
    }

    @Test
    void testPurchaseApprovePurchaseDoesNotExist() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();

        when(purchaseGetOne.getOne(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(
                PurchaseDoesNotExistException.class,
                () -> purchaseApproveService.execute(id, producerId)
        );
    }

    @Test
    void testPurchaseApproveIncorrectProducer() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var purchase = new Purchase(id, producerId, createdStatusId);

        var otherProducerId = UUID.randomUUID();

        when(purchaseGetOne.getOne(id)).thenReturn(Optional.of(purchase));
        Assertions.assertThrows(
                ProducerCannotApprovePurchaseException.class,
                () -> purchaseApproveService.execute(id, otherProducerId)
        );
    }

    @Test
    void testPurchaseApproveCannotBeApproved() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var purchaseStatusId = UUID.randomUUID();
        var purchase = new Purchase(id, producerId, purchaseStatusId);

        when(purchaseGetOne.getOne(id)).thenReturn(Optional.of(purchase));
        Assertions.assertThrows(
                PurchaseCannotBeApprovedException.class,
                () -> purchaseApproveService.execute(id, producerId)
        );
    }

    @Test
    void testPurchaseApprove() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();

        var purchase = new Purchase(id, producerId, createdStatusId);
        var approvedPurchase = new Purchase(id, producerId, approvedStatusId);
        var purchaseResponse = new PurchaseResponse(id, producerId, approvedStatusId);

        when(purchaseGetOne.getOne(id)).thenReturn(Optional.of(purchase));
        when(purchaseSetStatus.setStatus(id, approvedStatusId)).thenReturn(approvedPurchase);
        when(purchaseMapper.toPurchaseResponse(approvedPurchase)).thenReturn(purchaseResponse);

        var actualPurchaseResponse = purchaseApproveService.execute(id, producerId);
        assertThat(actualPurchaseResponse).isNotNull().isEqualTo(purchaseResponse);
    }

}
