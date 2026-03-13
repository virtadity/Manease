package com.virtadity.manease.application.service.producer.exception;

import java.util.UUID;

public class ProducerNotFoundException extends RuntimeException {
    public ProducerNotFoundException(String message) {
        super(message);
    }

    public static ProducerNotFoundException withId(UUID producerId) {
        var message = String.format("The producer not found with such id = %s", producerId);
        return new ProducerNotFoundException(message);
    }
}
