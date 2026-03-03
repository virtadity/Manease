package com.virtadity.manease.application.port.out.product;

import java.util.UUID;

public interface ProductDeleteOutputBoundary {
    void delete(UUID productId);
}
