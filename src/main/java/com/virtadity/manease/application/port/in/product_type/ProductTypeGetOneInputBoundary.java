package com.virtadity.manease.application.port.in.product_type;

import com.virtadity.manease.application.model.product_type.ProductTypeResponse;

import java.util.Optional;
import java.util.UUID;

public interface ProductTypeGetOneInputBoundary {
    ProductTypeResponse execute(UUID productTypeId);
}
