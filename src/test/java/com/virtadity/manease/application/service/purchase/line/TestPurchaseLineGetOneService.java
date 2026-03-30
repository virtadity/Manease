package com.virtadity.manease.application.service.purchase.line;

import com.virtadity.manease.application.mapper.PurchaseLineMapper;
import com.virtadity.manease.application.model.purchase.line.PurchaseLineResponse;
import com.virtadity.manease.application.port.out.purchase.line.PurchaseLineGetOneOutputBoundary;
import com.virtadity.manease.application.service.purchase.line.exception.PurchaseLineNotFoundException;
import com.virtadity.manease.domain.model.PurchaseLine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestPurchaseLineGetOneService {

    @Mock
    private PurchaseLineGetOneOutputBoundary purchaseLineGetOneStorage;

    @Mock
    private PurchaseLineMapper purchaseLineMapper;

    @InjectMocks
    private PurchaseLineGetOneService purchaseLineGetOneService;

    @Test
    void testPurchaseLineGetOne() {
        var purchaseId = UUID.randomUUID();
        var productId = UUID.randomUUID();
        var price = BigDecimal.valueOf(100.0);
        var quantity = BigInteger.valueOf(100);

        var purchase = new PurchaseLine(purchaseId, productId, price, quantity);
        var purchaseResponse = new PurchaseLineResponse(purchaseId, productId, price, quantity);

        when(purchaseLineMapper.toPurchaseLineResponse(purchase)).thenReturn(purchaseResponse);
        when(purchaseLineGetOneStorage.getOne(purchaseId, productId)).thenReturn(Optional.of(purchase));

        var actualPurchaseResponse = purchaseLineGetOneService.execute(purchaseId, productId);
        assertThat(actualPurchaseResponse)
                .isNotNull()
                .isEqualTo(purchaseResponse);

        var otherPurchaseId = UUID.randomUUID();
        var otherProductId = UUID.randomUUID();
        when(purchaseLineGetOneStorage.getOne(otherPurchaseId, otherProductId)).thenReturn(Optional.empty());

        Assertions.assertThrows(PurchaseLineNotFoundException.class,
                () -> purchaseLineGetOneService.execute(otherPurchaseId, otherProductId));
    }
}
