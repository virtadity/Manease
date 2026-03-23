package com.virtadity.manease.application.model.producer;

import java.util.UUID;

public record ProducerRequest(UUID producerId, String name) {}
