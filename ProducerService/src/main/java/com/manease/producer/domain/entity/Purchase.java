package com.manease.producer.domain.entity;

import java.util.UUID;

public record Purchase(UUID id, UUID producerId, UUID statusId) {}
