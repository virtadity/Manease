package com.virtadity.manease.domain.service.purchase_line;

import com.virtadity.manease.domain.model.Product;
import com.virtadity.manease.domain.model.PurchaseLine;
import com.virtadity.manease.domain.service.purchase_line.validation.ValidateProductInPurchaseLines;
import com.virtadity.manease.domain.service.purchase_line.validation.exception.ProductIsAlreadyInPurchaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestValidateProductInPurchaseLines {
    private final List<UUID> purchaseIdList = List.of(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
    );

    private final List<UUID> productIdList = List.of(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
    );

    private final List<BigDecimal> priceList = List.of(
            BigDecimal.valueOf(100.0),
            BigDecimal.valueOf(101.1),
            BigDecimal.valueOf(102.2),
            BigDecimal.valueOf(103.3),
            BigDecimal.valueOf(104.4)
    );

    private final List<BigInteger> quantityList = List.of(
            BigInteger.valueOf(10),
            BigInteger.valueOf(10),
            BigInteger.valueOf(10),
            BigInteger.valueOf(10),
            BigInteger.valueOf(10)
    );

    private List<PurchaseLine> purchaseLineList;

    private void setPurchaseLineList() {
        purchaseLineList = new ArrayList<>();
        for (int i = 0; i < purchaseIdList.size(); i++) {
            var purchaseId = purchaseIdList.get(i);
            var productId = productIdList.get(i);
            var price = priceList.get(i);
            var quantity = quantityList.get(i);
            var purchaseLine = new PurchaseLine(purchaseId, productId, price, quantity);
            purchaseLineList.add(purchaseLine);
        }
    }

    @BeforeEach
    void setUp() {
        setPurchaseLineList();
    }

    @Test
    void testValidateProductInPurchaseLines() {
        var productId = productIdList.getFirst();
        var productName = "Test name";
        var weight = BigDecimal.valueOf(100.0);
        var productProducerId = UUID.randomUUID();
        var productTypeId = UUID.randomUUID();
        var product = new Product(productId, productName, weight, productProducerId, productTypeId);

        assertThrows(
                ProductIsAlreadyInPurchaseException.class,
                () -> ValidateProductInPurchaseLines.execute(purchaseLineList, product)
        );

        var correctedProduct = new Product(UUID.randomUUID(), productName, weight, productProducerId, productTypeId);

        assertDoesNotThrow(
                () -> ValidateProductInPurchaseLines.execute(purchaseLineList, correctedProduct)
        );
    }
}
