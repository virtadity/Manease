package com.virtadity.manease.application.service.product_type.exception;

import com.virtadity.manease.application.exception.BusinessException;

import java.util.UUID;

public class ProductTypeNotFoundException extends BusinessException {
    public ProductTypeNotFoundException(String message) {
        super(message);
    }

    public static ProductTypeNotFoundException withId(UUID productTypeId) {
        var message = String.format("The productType not found with such id = %s", productTypeId);
        return new ProductTypeNotFoundException(message);
    }
}
