package com.virtadity.manease.infrastructure.database.dao.exception;

import java.util.UUID;

public class ProductEntityNotFoundException extends RuntimeException {
    public ProductEntityNotFoundException(String message) {
        super(message);
    }

    public static ProductEntityNotFoundException withSuchId(UUID productId) {
        var message = String.format("Product entity not found with such id = %s", productId);
        return new ProductEntityNotFoundException(message);
    }
}
