package com.manease.producer.application.service.purchase;

import com.manease.producer.application.entity.purchase.PurchaseResponse;
import com.manease.producer.application.mapper.purchase.PurchaseMapper;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetOneOutputBoundary;
import com.manease.producer.application.port.out.purchase.PurchaseSetStatusOutputBoundary;
import com.manease.producer.application.service.purchase.actions.PurchaseDeliverService;
import com.manease.producer.application.service.purchase.exception.ProducerCannotDeliverPurchaseException;
import com.manease.producer.application.service.purchase.exception.PurchaseCannotBeDeliveredException;
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
public class TestPurchaseDeliveredService {

    private UUID approvedStatusId;
    private UUID deliveredStatusId;

    @Mock
    private PurchaseGetOneOutputBoundary purchaseGetOne;

    @Mock
    private PurchaseSetStatusOutputBoundary purchaseSetStatus;

    @Mock
    private PurchaseMapper purchaseMapper;

    private PurchaseDeliverService purchaseDeliverService;

    @BeforeEach
    void setUp() {
        approvedStatusId = UUID.randomUUID();
        var statusApproved = new PurchaseStatus(approvedStatusId, "approved", "test description");

        deliveredStatusId = UUID.randomUUID();
        var statusDelivered = new PurchaseStatus(deliveredStatusId, "delivered", "test description");

        purchaseDeliverService = new PurchaseDeliverService(
                statusApproved,
                statusDelivered,
                purchaseGetOne,
                purchaseSetStatus,
                purchaseMapper
        );
    }

    @Test
    void testPurchaseDeliverPurchaseDoesNotExist() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();

        when(purchaseGetOne.execute(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(
                PurchaseDoesNotExistException.class,
                () -> purchaseDeliverService.execute(id, producerId)
        );
    }

    @Test
    void testPurchaseDeliverIncorrectProducer() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var purchase = new Purchase(id, producerId, approvedStatusId);
        var otherProducerId = UUID.randomUUID();

        when(purchaseGetOne.execute(id)).thenReturn(Optional.of(purchase));
        Assertions.assertThrows(
                ProducerCannotDeliverPurchaseException.class,
                () -> purchaseDeliverService.execute(id, otherProducerId)
        );
    }

    @Test
    void testPurchaseDeliverCannotBeDelivered() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var purchaseStatusId = UUID.randomUUID();
        var purchase = new Purchase(id, producerId, purchaseStatusId);

        when(purchaseGetOne.execute(id)).thenReturn(Optional.of(purchase));
        Assertions.assertThrows(
                PurchaseCannotBeDeliveredException.class,
                () -> purchaseDeliverService.execute(id, producerId)
        );
    }

    @Test
    void testPurchaseDeliver() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var purchase = new Purchase(id, producerId, approvedStatusId);
        var deliveredPurchase = new Purchase(id, producerId, deliveredStatusId);
        var purchaseResponse = new PurchaseResponse(id, producerId, deliveredStatusId);

        when(purchaseGetOne.execute(id)).thenReturn(Optional.of(purchase));
        when(purchaseSetStatus.execute(id, deliveredStatusId)).thenReturn(deliveredPurchase);
        when(purchaseMapper.toPurchaseResponse(deliveredPurchase)).thenReturn(purchaseResponse);

        var actualPurchaseResponse = purchaseDeliverService.execute(id, producerId);
        assertThat(actualPurchaseResponse).isNotNull().isEqualTo(purchaseResponse);
    }
}
