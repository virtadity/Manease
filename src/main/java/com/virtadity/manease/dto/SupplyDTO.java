package com.virtadity.manease.dto;


import java.time.LocalDate;

public record SupplyDTO(
        Long id,
        Long manufacturerId,
        String description,
        LocalDate deliveryDate,
        LocalDate creationDate
) {}
