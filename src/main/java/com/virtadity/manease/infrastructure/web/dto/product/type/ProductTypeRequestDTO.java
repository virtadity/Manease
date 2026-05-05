package com.virtadity.manease.infrastructure.web.dto.product.type;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductTypeRequestDTO {
    private UUID id;
    private String name;
}
