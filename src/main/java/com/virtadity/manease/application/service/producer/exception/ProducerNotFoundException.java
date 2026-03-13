package com.virtadity.manease.application.service.producer.exception;

import com.virtadity.manease.application.exception.BusinessException;

import java.util.UUID;

public class ProducerNotFoundException extends BusinessException {
    public ProducerNotFoundException(String message) {
        super(message);
    }

    public static ProducerNotFoundException withId(UUID producerId) {
        var message = String.format("The producer not found with such id = %s", producerId);
        return new ProducerNotFoundException(message);
    }
}
