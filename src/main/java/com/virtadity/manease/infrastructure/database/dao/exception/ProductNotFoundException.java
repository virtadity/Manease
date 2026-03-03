package com.virtadity.manease.infrastructure.database.dao.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException withSuchId(UUID productId) {
        var message = String.format("Product not found with such id = %s", productId);
        return new ProductNotFoundException(message);
    }
}
