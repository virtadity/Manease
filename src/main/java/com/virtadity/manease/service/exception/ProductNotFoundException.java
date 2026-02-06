package com.virtadity.manease.service.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException withId(Long productId) {
        return new ProductNotFoundException("Product not found with such id = " + productId);
    }
}
