package com.virtadity.manease.infrastructure.database.dao.exception;

import com.virtadity.manease.application.exception.BusinessException;

import java.util.UUID;

public class ProductTypeEntityNotFoundException extends BusinessException {
    public ProductTypeEntityNotFoundException(String message) {
        super(message);
    }

    public static ProductTypeEntityNotFoundException withSuchId(UUID productTypeId) {
        var message = String.format("ProductType entity not found with such id = %s", productTypeId);
        return new ProductTypeEntityNotFoundException(message);
    }
}
