package com.virtadity.manease.infrastructure.database.dao.exception;

import java.util.UUID;

public class ProducerNotFoundException extends RuntimeException {
    public ProducerNotFoundException(String message) {
        super(message);
    }

    public static ProducerNotFoundException withSuchId(UUID producerId) {
        var message = String.format("Producer not found with such id = %s", producerId);
        return new ProducerNotFoundException(message);
    }
}
