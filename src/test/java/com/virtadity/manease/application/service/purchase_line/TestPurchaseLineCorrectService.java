package com.virtadity.manease.application.service.purchase_line;

import com.virtadity.manease.application.mapper.PurchaseLineMapper;
import com.virtadity.manease.application.model.purchase_line.PurchaseLineRequest;
import com.virtadity.manease.application.model.purchase_line.PurchaseLineResponse;
import com.virtadity.manease.application.port.out.purchase_line.PurchaseLineCorrectOutputBoundary;
import com.virtadity.manease.domain.model.PurchaseLine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestPurchaseLineCorrectService {

    @Mock
    private PurchaseLineMapper purchaseLineMapper;

    @Mock
    private PurchaseLineCorrectOutputBoundary purchaseLineCorrectStorage;

    @InjectMocks
    private PurchaseLineCorrectService purchaseLineCorrectService;

    @Test
    void testCorrectPurchaseLine() {

        var purchaseId = UUID.randomUUID();
        var productId = UUID.randomUUID();
        var price = BigDecimal.valueOf(100.0);
        var quantity = BigInteger.valueOf(100);

        var purchaseLineForUpdate = new PurchaseLine(purchaseId, productId, price, quantity);
        var purchaseLineRequestForUpdate = new PurchaseLineRequest(purchaseId, productId, price, quantity);
        var purchaseLineResponseForUpdate = new PurchaseLineResponse(purchaseId, productId, price, quantity);

        when(purchaseLineMapper.toPurchaseLine(purchaseLineRequestForUpdate)).thenReturn(purchaseLineForUpdate);
        when(purchaseLineMapper.toPurchaseLineResponse(purchaseLineForUpdate))
                .thenReturn(purchaseLineResponseForUpdate);

        when(purchaseLineCorrectStorage.correct(purchaseId, productId, purchaseLineForUpdate))
                .thenReturn(purchaseLineForUpdate);

        var actualPurchaseLineResponse = purchaseLineCorrectService
                .execute(purchaseId, productId, purchaseLineRequestForUpdate);

        assertThat(actualPurchaseLineResponse)
                .isNotNull()
                .isEqualTo(purchaseLineResponseForUpdate);
    }

}
