package com.virtadity.manease.infrastructure.web.dto.producer;

import lombok.Data;

import java.util.UUID;

@Data
public class ProducerResponseDTO{
    private UUID id;
    private String name;
}
