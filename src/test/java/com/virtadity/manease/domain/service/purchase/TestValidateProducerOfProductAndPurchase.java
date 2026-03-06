package com.virtadity.manease.domain.service.purchase;

import com.virtadity.manease.domain.model.Product;
import com.virtadity.manease.domain.model.Purchase;
import com.virtadity.manease.domain.service.purchase.validation.ValidateProducerOfProductAndPurchase;
import com.virtadity.manease.domain.service.purchase.validation.exception.ProducerIsDifferentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


public class TestValidateProducerOfProductAndPurchase {

    @Test
    void testValidate() {
        var purchaseId = UUID.randomUUID();
        var description = "Test purchase";
        var creationDate = LocalDateTime.now();
        var deliveryDate = LocalDateTime.now();
        var purchaseProducerId = UUID.randomUUID();

        var productId = UUID.randomUUID();
        var productName = "Test name";
        var weight = BigDecimal.valueOf(100.0);
        var productProducerId = UUID.randomUUID();
        var productTypeId = UUID.randomUUID();

        var purchase = new Purchase(purchaseId, description, creationDate, deliveryDate, purchaseProducerId);
        var product = new Product(productId, productName, weight, productProducerId, productTypeId);

        Assertions.assertThrows(
                ProducerIsDifferentException.class,
                () -> ValidateProducerOfProductAndPurchase.execute(purchase, product)
        );

        var correctedProduct = new Product(productId, productName, weight, purchaseProducerId, productTypeId);
        Assertions.assertDoesNotThrow(
                () -> ValidateProducerOfProductAndPurchase.execute(purchase, correctedProduct)
        );
    }
}
