package com.virtadity.manease.application.port.out.product;

import com.virtadity.manease.domain.model.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductGetOneOutputBoundary {
    Optional<Product> execute(UUID productId);
}
