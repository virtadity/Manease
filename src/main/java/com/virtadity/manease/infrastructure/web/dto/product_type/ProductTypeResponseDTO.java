package com.virtadity.manease.infrastructure.web.dto.product_type;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductTypeResponseDTO {
    private UUID id;
    private String name;
}
