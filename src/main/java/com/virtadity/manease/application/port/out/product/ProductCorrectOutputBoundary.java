package com.virtadity.manease.application.port.out.product;

import com.virtadity.manease.domain.model.Product;

import java.util.UUID;

public interface ProductCorrectOutputBoundary {
    Product execute(UUID productId, Product product);
}
