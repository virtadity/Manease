package com.virtadity.manease.service.exception;

public class SupplyNotFoundException extends RuntimeException {
    public SupplyNotFoundException(String message) {
        super(message);
    }

    public static SupplyNotFoundException withId(Long id) {
        return new SupplyNotFoundException("Supply not with such id = " + id);
    }
}
