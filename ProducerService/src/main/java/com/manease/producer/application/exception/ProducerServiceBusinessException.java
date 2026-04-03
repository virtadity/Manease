package com.manease.producer.application.exception;

public class ProducerServiceBusinessException extends RuntimeException {
    public ProducerServiceBusinessException(String message) {
        super(message);
    }
}
