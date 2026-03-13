package com.virtadity.manease.infrastructure.database.dao.exception;

import com.virtadity.manease.application.exception.BusinessException;

import java.util.UUID;

public class ProducerEntityNotFoundException extends BusinessException {
    public ProducerEntityNotFoundException(String message) {
        super(message);
    }

    public static ProducerEntityNotFoundException withSuchId(UUID producerId) {
        var message = String.format("Producer entity not found with such id = %s", producerId);
        return new ProducerEntityNotFoundException(message);
    }
}
