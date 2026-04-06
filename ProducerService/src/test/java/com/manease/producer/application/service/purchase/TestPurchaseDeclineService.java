package com.manease.producer.application.service.purchase;

import com.manease.producer.application.entity.purchase.PurchaseResponse;
import com.manease.producer.application.mapper.purchase.PurchaseMapper;
import com.manease.producer.application.port.out.purchase.getters.PurchaseGetOneOutputBoundary;
import com.manease.producer.application.port.out.purchase.PurchaseSetStatusOutputBoundary;
import com.manease.producer.application.service.purchase.actions.PurchaseDeclineService;
import com.manease.producer.application.service.purchase.exception.ProducerCannotDeclinePurchaseException;
import com.manease.producer.application.service.purchase.exception.PurchaseCannotBeDeclinedException;
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
public class TestPurchaseDeclineService {

    private UUID approvedStatusId;
    private UUID createdStatusId;
    private UUID declinedStatusId;

    @Mock
    private PurchaseGetOneOutputBoundary purchaseGetOne;

    @Mock
    private PurchaseSetStatusOutputBoundary purchaseSetStatus;

    @Mock
    private PurchaseMapper purchaseMapper;

    private PurchaseDeclineService purchaseDeclineService;

    @BeforeEach
    void testUp() {
        approvedStatusId = UUID.randomUUID();
        var purchaseStatusApproved = new PurchaseStatus(
                approvedStatusId,
                "test approved status name",
                "test approved status description"
        );

        createdStatusId = UUID.randomUUID();
        var purchaseStatusCreated = new PurchaseStatus(
                createdStatusId,
                "test created status name",
                "test created status description"
        );

        declinedStatusId = UUID.randomUUID();
        var purchaseStatusDeclined = new PurchaseStatus(
                declinedStatusId,
                "test declined status name",
                "test declined status description"
        );

        purchaseDeclineService = new PurchaseDeclineService(
                purchaseStatusApproved,
                purchaseStatusCreated,
                purchaseStatusDeclined,
                purchaseGetOne,
                purchaseSetStatus,
                purchaseMapper
        );

    }

    @Test
    void testPurchaseDeclineDoesNotExist() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();

        when(purchaseGetOne.getOne(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(
                PurchaseDoesNotExistException.class,
                () -> purchaseDeclineService.execute(id, producerId)
        );
    }

    @Test
    void testPurchaseDeclineIncorrectProducer() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var purchase = new Purchase(id, producerId, createdStatusId);

        var otherProducerId = UUID.randomUUID();
        when(purchaseGetOne.getOne(id)).thenReturn(Optional.of(purchase));
        Assertions.assertThrows(
                ProducerCannotDeclinePurchaseException.class,
                () -> purchaseDeclineService.execute(id, otherProducerId)
        );
    }

    @Test
    void testPurchaseDeclineCannotBeDeclined() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var purchaseStatusId = UUID.randomUUID();
        var purchase = new Purchase(id, producerId, purchaseStatusId);

        when(purchaseGetOne.getOne(id)).thenReturn(Optional.of(purchase));
        Assertions.assertThrows(
                PurchaseCannotBeDeclinedException.class,
                () -> purchaseDeclineService.execute(id, producerId)
        );
    }

    @Test
    void testPurchaseDeclineWhenPurchaseCreated() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();

        var purchase = new Purchase(id, producerId, createdStatusId);
        var declinedPurchase = new Purchase(id, producerId, declinedStatusId);
        var purchaseResponse = new PurchaseResponse(id, producerId, declinedStatusId);

        when(purchaseGetOne.getOne(id)).thenReturn(Optional.of(purchase));
        when(purchaseSetStatus.setStatus(id, declinedStatusId)).thenReturn(declinedPurchase);
        when(purchaseMapper.toPurchaseResponse(declinedPurchase)).thenReturn(purchaseResponse);

        var actualPurchaseResponse = purchaseDeclineService.execute(id, producerId);
        assertThat(actualPurchaseResponse).isNotNull().isEqualTo(purchaseResponse);
    }

    @Test
    void testPurchaseDeclineWhenPurchaseApproved() {
        var id = UUID.randomUUID();
        var producerId = UUID.randomUUID();

        var purchase = new Purchase(id, producerId, approvedStatusId);
        var declinedPurchase = new Purchase(id, producerId, declinedStatusId);
        var purchaseResponse = new PurchaseResponse(id, producerId, declinedStatusId);

        when(purchaseGetOne.getOne(id)).thenReturn(Optional.of(purchase));
        when(purchaseSetStatus.setStatus(id, declinedStatusId)).thenReturn(declinedPurchase);
        when(purchaseMapper.toPurchaseResponse(declinedPurchase)).thenReturn(purchaseResponse);

        var actualPurchaseResponse = purchaseDeclineService.execute(id, producerId);
        assertThat(actualPurchaseResponse).isNotNull().isEqualTo(purchaseResponse);
    }

}
