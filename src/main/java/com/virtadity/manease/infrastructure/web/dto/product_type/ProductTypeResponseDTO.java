package com.virtadity.manease.infrastructure.web.dto.product_type;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductTypeResponseDTO {
    private UUID id;
    private String name;
}
