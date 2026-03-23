package com.virtadity.manease.application.service.purchase;

import com.virtadity.manease.application.mapper.PurchaseMapper;
import com.virtadity.manease.application.model.purchase.PurchaseRequest;
import com.virtadity.manease.application.model.purchase.PurchaseResponse;
import com.virtadity.manease.application.port.out.purchase.PurchaseCorrectOutputBoundary;
import com.virtadity.manease.domain.model.Purchase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TestPurchaseCorrectService {

    @Mock
    private PurchaseMapper purchaseMapper;

    @Mock
    private PurchaseCorrectOutputBoundary purchaseCorrectStorage;

    @InjectMocks
    private PurchaseCorrectService purchaseCorrectService;

    @Test
    void testPurchaseCorrect() {
        var purchaseId = UUID.randomUUID();
        var description = "Test purchase";
        var producerId = UUID.randomUUID();
        var creationDate = LocalDateTime.now();
        var deliveryDate = LocalDateTime.now();

        var purchaseRequest = new PurchaseRequest(purchaseId, description, creationDate, deliveryDate, producerId);
        var purchase = new Purchase(purchaseId, description, creationDate, deliveryDate, producerId);
        var purchaseResponse = new PurchaseResponse(purchaseId, description, creationDate, deliveryDate, producerId);

        when(purchaseMapper.toPurchase(purchaseRequest)).thenReturn(purchase);
        when(purchaseMapper.toPurchaseResponse(purchase)).thenReturn(purchaseResponse);
        when(purchaseCorrectStorage.correct(purchaseId, purchase)).thenReturn(purchase);

        var actualPurchaseResponse = purchaseCorrectService.execute(purchaseId, purchaseRequest);

        assertThat(actualPurchaseResponse)
                .isNotNull()
                .isEqualTo(purchaseResponse);
    }
}
