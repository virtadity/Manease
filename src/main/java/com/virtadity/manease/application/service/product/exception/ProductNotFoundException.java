package com.virtadity.manease.application.service.product.exception;


import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException withId(UUID productId) {
        var message = String.format("Product not found with such id = %s", productId);
        return new ProductNotFoundException(message);
    }
}
