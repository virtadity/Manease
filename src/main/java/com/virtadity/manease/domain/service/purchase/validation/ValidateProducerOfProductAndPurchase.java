package com.virtadity.manease.domain.service.purchase.validation;

import com.virtadity.manease.domain.model.Product;
import com.virtadity.manease.domain.model.Purchase;
import com.virtadity.manease.domain.service.purchase.validation.exception.ProducerIsDifferentException;

/**
 * This class validates products in a purchase provided by same producer.
 */
public class ValidateProducerOfProductAndPurchase {
    public static void execute(Purchase purchase, Product product) {
        if (!purchase.producerId().equals(product.producerId())) {
            throw ProducerIsDifferentException.withIds(purchase.purchaseId(), product.productId());
        }
    }
}
