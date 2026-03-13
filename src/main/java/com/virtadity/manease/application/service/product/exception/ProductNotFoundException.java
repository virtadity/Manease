package com.virtadity.manease.application.service.product.exception;


import com.virtadity.manease.application.exception.BusinessException;

import java.util.UUID;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException withId(UUID productId) {
        var message = String.format("Product not found with such id = %s", productId);
        return new ProductNotFoundException(message);
    }
}
