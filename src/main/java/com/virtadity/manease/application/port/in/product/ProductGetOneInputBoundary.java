package com.virtadity.manease.application.port.in.product;

import com.virtadity.manease.application.model.product.ProductResponse;

import java.util.Optional;
import java.util.UUID;

public interface ProductGetOneInputBoundary {
    ProductResponse execute(UUID productId);
}
