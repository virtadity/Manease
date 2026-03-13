package com.virtadity.manease.application.exception;

import java.util.UUID;

public class ProductDoesNotExistException extends BusinessException {
    public ProductDoesNotExistException(String message) {
        super(message);
    }

    public static ProductDoesNotExistException withProductId(UUID productId) {
        var message = String.format("Product does not exist with such id = %s", productId);
        return new ProductDoesNotExistException(message);
    }
}
