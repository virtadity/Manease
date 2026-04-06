package com.manease.producer.application.service.purchase;

import com.manease.producer.application.entity.purchase.PurchaseResponse;
import com.manease.producer.application.mapper.purchase.PurchaseMapper;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetOneOutputBoundary;
import com.manease.producer.application.port.out.purchase.PurchaseSetStatusOutputBoundary;
import com.manease.producer.application.service.purchase.actions.PurchaseDeleteService;
import com.manease.producer.application.service.purchase.exception.ProducerCannotDeclinePurchaseException;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestPurchaseDeleteService {

    private UUID deletedStatusId;

    @Mock
    private PurchaseGetOneOutputBoundary purchaseGetOne;

    @Mock
    private PurchaseSetStatusOutputBoundary purchaseSetStatus;

    @Mock
    private PurchaseMapper purchaseMapper;

    private PurchaseDeleteService purchaseDeleteService;

    @BeforeEach
    void setUp() {
        deletedStatusId = UUID.randomUUID();
        var deletedPurchaseStatus = new PurchaseStatus(
                deletedStatusId,
                "deleted",
                "test description"
        );

        purchaseDeleteService = new PurchaseDeleteService(
                deletedPurchaseStatus,
                purchaseGetOne,
                purchaseSetStatus,
                purchaseMapper
        );
    }

    @Test
    void testPurchaseDeletePurchaseDoesNotExist() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();

        when(purchaseGetOne.getOne(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(
                PurchaseDoesNotExistException.class,
                () -> purchaseDeleteService.execute(id, producerId)
        );
    }

    @Test
    void testPurchaseDeleteIncorrectProducer() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var createdStatusId = UUID.randomUUID();
        var purchase = new Purchase(id, producerId, createdStatusId);

        var otherProducerId = UUID.randomUUID();

        when(purchaseGetOne.getOne(id)).thenReturn(Optional.of(purchase));
        Assertions.assertThrows(
                ProducerCannotDeclinePurchaseException.class,
                () -> purchaseDeleteService.execute(id, otherProducerId)
        );
    }

    @Test
    void testPurchaseDelete() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var createdStatusId = UUID.randomUUID();
        var purchase = new Purchase(id, producerId, createdStatusId);
        var deletedPurchase = new Purchase(id, producerId, deletedStatusId);
        var purchaseResponse = new PurchaseResponse(id, producerId, deletedStatusId);

        when(purchaseGetOne.getOne(id)).thenReturn(Optional.of(purchase));
        when(purchaseSetStatus.setStatus(id, deletedStatusId)).thenReturn(deletedPurchase);
        when(purchaseMapper.toPurchaseResponse(deletedPurchase)).thenReturn(purchaseResponse);

        var actualPurchaseResponse = purchaseDeleteService.execute(id, producerId);
        assertThat(actualPurchaseResponse).isNotNull().isEqualTo(purchaseResponse);
    }
}
