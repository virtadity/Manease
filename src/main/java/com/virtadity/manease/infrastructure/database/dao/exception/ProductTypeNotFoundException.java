package com.virtadity.manease.infrastructure.database.dao.exception;

import java.util.UUID;

public class ProductTypeNotFoundException extends RuntimeException {
    public ProductTypeNotFoundException(String message) {
        super(message);
    }

    public static ProductTypeNotFoundException withSuchId(UUID productTypeId) {
        var message = String.format("ProductType not found with such id = %s", productTypeId);
        return new ProductTypeNotFoundException(message);
    }
}
