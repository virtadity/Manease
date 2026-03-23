package com.virtadity.manease.application.port.in.product;

import com.virtadity.manease.application.model.product.ProductRequest;
import com.virtadity.manease.application.model.product.ProductResponse;

import java.util.UUID;

public interface ProductCorrectInputBoundary {
    ProductResponse execute(UUID productId, ProductRequest productRequest);
}
