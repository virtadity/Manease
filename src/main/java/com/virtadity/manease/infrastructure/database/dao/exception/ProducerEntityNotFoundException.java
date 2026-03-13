package com.virtadity.manease.infrastructure.database.dao.exception;

import java.util.UUID;

public class ProducerEntityNotFoundException extends RuntimeException {
    public ProducerEntityNotFoundException(String message) {
        super(message);
    }

    public static ProducerEntityNotFoundException withSuchId(UUID producerId) {
        var message = String.format("Producer entity not found with such id = %s", producerId);
        return new ProducerEntityNotFoundException(message);
    }
}
