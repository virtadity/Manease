package com.virtadity.manease.infrastructure.web.dto.product_type;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductTypeRequestDTO {
    private UUID id;
    private String name;
}
