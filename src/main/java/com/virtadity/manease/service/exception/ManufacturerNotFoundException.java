package com.virtadity.manease.service.exception;

public class ManufacturerNotFoundException extends RuntimeException {
    public ManufacturerNotFoundException(String message) {
        super(message);
    }

    public static ManufacturerNotFoundException withId(Long id) {
        var message = "Not found Manufacturer with such id = " + id;
        return new ManufacturerNotFoundException(message);
    }
}
