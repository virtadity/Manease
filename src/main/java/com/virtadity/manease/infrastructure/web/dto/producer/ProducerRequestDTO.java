package com.virtadity.manease.infrastructure.web.dto.producer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProducerRequestDTO{
    private UUID id;
    private String name;
 }
