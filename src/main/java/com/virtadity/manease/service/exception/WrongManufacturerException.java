package com.virtadity.manease.service.exception;

public class WrongManufacturerException extends RuntimeException {
    public WrongManufacturerException(String message) {
        super(message);
    }

    public static WrongManufacturerException withId(Long manufacturerId, Long productId) {
        var message = String.format(
                "Manufacturer with id = %d not produces Product with id = %d",
                manufacturerId,
                productId
        );
        return new WrongManufacturerException(message);
    }
}
