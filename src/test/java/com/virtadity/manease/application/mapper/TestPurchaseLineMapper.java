package com.virtadity.manease.application.mapper;

import com.virtadity.manease.application.model.purchase.line.PurchaseLineRequest;
import com.virtadity.manease.application.model.purchase.line.PurchaseLineResponse;
import com.virtadity.manease.domain.model.PurchaseLine;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestPurchaseLineMapper {
    private final PurchaseLineMapper purchaseLineMapper = new PurchaseLineMapperImpl();

    @Test
    void testPurchaseLineRequestToPurchaseLine() {
        var purchaseId = UUID.randomUUID();
        var productId = UUID.randomUUID();
        var price = BigDecimal.valueOf(100.0);
        var quantity = BigInteger.valueOf(100);

        var purchaseLineRequest = new PurchaseLineRequest(purchaseId, productId, price, quantity);
        var expectedPurchaseLine = new PurchaseLine(purchaseId, productId, price, quantity);
        var actualPurchaseLine = purchaseLineMapper.toPurchaseLine(purchaseLineRequest);

        assertThat(actualPurchaseLine).isNotNull().isEqualTo(expectedPurchaseLine);
    }

    @Test
    void testPurchaseLineToPurchaseLineResponse() {
        var purchaseId = UUID.randomUUID();
        var productId = UUID.randomUUID();
        var price = BigDecimal.valueOf(100.0);
        var quantity = BigInteger.valueOf(100);

        var purchaseLine = new PurchaseLine(purchaseId, productId, price, quantity);
        var expectedPurchaseLineResponse = new PurchaseLineResponse(purchaseId, productId, price, quantity);
        var actualPurchaseLineResponse = purchaseLineMapper.toPurchaseLineResponse(purchaseLine);

        assertThat(actualPurchaseLineResponse).isNotNull().isEqualTo(expectedPurchaseLineResponse);
    }
}
