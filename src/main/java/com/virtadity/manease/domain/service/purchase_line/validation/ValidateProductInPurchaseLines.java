package com.virtadity.manease.domain.service.purchase_line.validation;

import com.virtadity.manease.domain.model.Product;
import com.virtadity.manease.domain.model.PurchaseLine;
import com.virtadity.manease.domain.service.purchase_line.validation.exception.ProductIsAlreadyInPurchaseException;

import java.util.List;

/**
 * This class validates uniqueness of a product in a purchase.
 * It necessary for non-ambiguous addition a product in a purchase.
 */
public class ValidateProductInPurchaseLines {
    public static void execute(List<PurchaseLine> purchaseLineList, Product product) {
        var isProductAlreadyInPurchase = purchaseLineList
                .stream()
                .map(PurchaseLine::productId)
                .anyMatch(product.productId()::equals);

        if (isProductAlreadyInPurchase) {
            throw ProductIsAlreadyInPurchaseException.withIds(
                    product.productId(),
                    purchaseLineList.getFirst().purchaseId()
            );
        }
    }
}
