package com.virtadity.manease.service.exception;

public class ProductTypeNotFoundException extends RuntimeException {
    public ProductTypeNotFoundException(String message) {
        super(message);
    }

    public static ProductTypeNotFoundException withId(Long id) {
        var message = "ProductType not found with such id = " + id;
        return new ProductTypeNotFoundException(message);
    }
}
