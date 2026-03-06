package com.virtadity.manease.application.service.purchase;

import com.virtadity.manease.application.mapper.PurchaseMapper;
import com.virtadity.manease.application.model.purchase.PurchaseResponse;
import com.virtadity.manease.application.port.out.purchase.PurchaseGetOneOutputBoundary;
import com.virtadity.manease.domain.model.Purchase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestPurchaseGetOneService {

    @Mock
    private PurchaseMapper purchaseMapper;

    @Mock
    private PurchaseGetOneOutputBoundary purchaseGetOneStorage;

    @InjectMocks
    private PurchaseGetOneService purchaseGetOneService;

    @Test
    void testPurchaseGetOne() {
        var purchaseId = UUID.randomUUID();
        var otherPurchaseId = UUID.randomUUID();
        var description = "Test purchase";
        var producerId = UUID.randomUUID();
        var creationDate = LocalDateTime.now();
        var deliveryDate = LocalDateTime.now();

        var purchase = new Purchase(purchaseId, description, creationDate, deliveryDate, producerId);
        var purchaseResponse = new PurchaseResponse(purchaseId, description, creationDate, deliveryDate, producerId);

        when(purchaseGetOneStorage.getOne(purchaseId)).thenReturn(Optional.of(purchase));
        when(purchaseGetOneStorage.getOne(otherPurchaseId)).thenReturn(Optional.empty());
        when(purchaseMapper.toPurchaseResponse(purchase)).thenReturn(purchaseResponse);

        var actualPurchaseResponse = purchaseGetOneService.execute(purchaseId);
        assertThat(actualPurchaseResponse)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(Optional.of(purchaseResponse));

        var emptyPurchaseResponse = purchaseGetOneService.execute(otherPurchaseId);
        assertThat(emptyPurchaseResponse)
                .isNotNull()
                .isEmpty();
    }
}
