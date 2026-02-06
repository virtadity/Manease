package com.virtadity.manease.dto;

public record SupplyDTO(
        Long id,
        Long manufacturerId,
        String description,
        String deliveryDate,
        String creationDate
) {}
